import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.regex.Pattern;

public class NackaLoader {

	//URL Subject to change
	private static final String ALL_TEAMS_URL = "http://www.nackakickball.com/scheduler/aplsteamlist.htm";
	
	//Only true while parsing http://www.nackakickball.com/scheduler/aplsteamlist.htm
	private static final  String LIST_ELEMENT = "<li>";
	private static final  String CLOSE_LIST_ELEMENT = "</ul>";
	private static final  String HR_ELEMENT = "<hr>";
	private static final  String TR_ELEMENT = "<tr>";
	private static final  String CLOSE_TABLE_ELEMENT = "</table>";

	/**
	 * Test my algorithms
	 */
	public static void main(String[] args) throws IOException {
		
		//If testing @ work
		if( System.getProperty("user.name").equals("rbucinell"))
		{
			System.setProperty("http.proxyHost", "proxy.useastgw.xerox.com");
			System.setProperty("http.proxyPort", "8000");
		}
		
		//Test the GetCurrentSeasonTeamData() data
		Hashtable<String, TeamElement> teams = GetCurrentSeasonTeamData();
		Iterator<String> iterator = teams.keySet().iterator();
		while( iterator.hasNext() )
		{
			String key = (String) iterator.next();
			TeamElement curTeam = teams.get(key);
			
			ArrayList<ScheduledMatchUp> schedule = GetTeamSchedule( curTeam.getName(), "http://www.nackakickball.com/scheduler/" + curTeam.getPage() );
			
			Iterator<ScheduledMatchUp> sIter = schedule.iterator();
			while( sIter.hasNext() )
			{
				ScheduledMatchUp match = sIter.next();
				System.out.println( match );
			}
			
			System.out.println("********************************");
			
		}
		
		
	}

	
	/**
	 * Retrieves the HTML code from a given URL
	 * @param someUrl The website to extract the data from
	 * @return The string representation of a webpage
	 * @throws ConnectException if the operation times out a ConnectException will be thrown
	 * @throws IOException if the operation fails to read the data stream from the page an IO exception will be thrown
	 */
	private static String getHtmlFromUrl( String someUrl ) throws ConnectException, IOException
	{
		URL url = new URL(someUrl);
		URLConnection con = url.openConnection();
		BufferedReader br = new BufferedReader( new InputStreamReader(con.getInputStream()));
		StringBuilder sb = new StringBuilder();
		
		String input = "";
		while( (input = br.readLine())!= null)
		{
			sb.append(input);
		}
		return sb.toString();
	}
	
	/**
	 * Gets the current season's Team Schedule list and returns all the teams with their data
	 * @return A key-value pair table of the data sthat is exrtacted from the website 
	 */
	public static Hashtable<String, TeamElement> GetCurrentSeasonTeamData()
	{
		//Gets the html page
		String allTeamsHtml = Get_AllTeams_HTMLPage();
		
		//Remove all the HMTL until the list elements are left in the page
		allTeamsHtml = allTeamsHtml.substring(allTeamsHtml.indexOf(LIST_ELEMENT),allTeamsHtml.indexOf(CLOSE_LIST_ELEMENT));
		
		//Parse the list, and return the table
		return ParseTeams( allTeamsHtml );
	}
	
	/**
	 * parses the data of the list of data passed from a partially read HTML page
	 * @param htmlList <li> elements of the page's list
	 * @return the data extracted from the raw HTML
	 */
	private static Hashtable<String, TeamElement> ParseTeams(String htmlList) {
		Hashtable<String, TeamElement> teamsFound = new Hashtable<String, TeamElement>();
		
		String[] listElements = htmlList.split("(<li>)");
		
		for( int i = 0; i < listElements.length; i++ )
		{
			String curElement = listElements[i];
			if( curElement.equals(""))
				continue;

			String uncleanTeamValues = curElement.split("\".*?\"")[1];
			String teamValues = uncleanTeamValues.substring(1, uncleanTeamValues.indexOf("</a>"));
			String teamName = teamValues.substring(0, teamValues.indexOf("("));
			String teamColor = teamValues.substring(teamValues.indexOf("(")+1, teamValues.indexOf(")"));
			
			String uncleanTeamPage = curElement.split("(?<=^|>)[^><]+?(?=<|$)",	Pattern.CASE_INSENSITIVE | Pattern.DOTALL)[0];
			String teamPage = uncleanTeamPage.substring( uncleanTeamPage.indexOf("=\"")+2, uncleanTeamPage.indexOf("\">"));

			teamName = teamName.trim();
			teamPage = teamPage.trim();
			teamColor = teamColor.trim();

			TeamElement element = new TeamElement( teamName, teamPage, teamColor );
			teamsFound.put(teamName, element);
			
		}
		return teamsFound;
	}

	/**
	 * Gets the HTML from the current all team's schedule list
	 * @return a string of the HTML code
	 */
	private static String Get_AllTeams_HTMLPage()
	{
		try{
			return getHtmlFromUrl( ALL_TEAMS_URL );
		}catch( Exception e )
		{
			return "";
		}
	}
	
	public static ArrayList<ScheduledMatchUp> GetTeamSchedule( String teamName, String teamPageUrl )
	{
		ArrayList<ScheduledMatchUp> list = new ArrayList<ScheduledMatchUp>();
		
		String teamScheduleHtml = "";
		try
		{
			teamScheduleHtml = getHtmlFromUrl( teamPageUrl );
		}
		catch( Exception e )
		{
			return list;
		}
		
		//TODO parsing here
		String html = teamScheduleHtml.substring(teamScheduleHtml.indexOf(HR_ELEMENT)); //Truncate Early code
		html = html.substring( html.indexOf(TR_ELEMENT), html.indexOf(CLOSE_TABLE_ELEMENT)); //Finish trucating all of the code outside of the table
		
		String[] tableRows = html.split("(</tr>)");
		
		for( int row = 0; row < tableRows.length; row++ )
		{
			String curRow = tableRows[row];
			curRow = curRow.substring(curRow.indexOf("<TD>"));//puts us at the first piece of data
			String[] vals = ExtractMatchupFromTD( curRow );
			ScheduledMatchUp tempMatchup = new ScheduledMatchUp(teamName, vals[0], vals[1], vals[2], vals[3], vals[4], vals[5]);
			list.add(tempMatchup);			
		}
		return list;
	}

	/**
	 * Reads in a HTML table row of TDs, returns the matchup data
	 * @param htmlRow the TDs in the table row
	 * @return A ScheduledMatchUp of the matchup data
	 * 
	 */
	private static String[] ExtractMatchupFromTD(String htmlRow) {
		
		String r = htmlRow.replace("<TD>", "[").replace("</td>", "]");
		int index = 0;
		String[] values = new String[6];
		for( int i = 0; i < 6; i++)
		{
			int sIndex = r.indexOf("[",index)+1;
			int eIndex = r.indexOf("]", index);
			String curValue = r.substring( sIndex, eIndex);
			values[i] = curValue;
			index = eIndex+1;
		}
		return values;
	}
}
