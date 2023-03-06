package com.nacka_kickball.Tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Pattern;

import android.util.Log;

import com.nacka_kickball.Data.ScheduledMatchUp;
import com.nacka_kickball.Data.TShirtColor;
import com.nacka_kickball.Data.TeamElement;

public class NackaHTML {

	private static final String LOG_TAG = "NACKA_HTML_FUNCTIONS";

	//URL Subject to change
	public static final String ALL_TEAMS_URL = "http://www.nackakickball.com/scheduler/aplsteamlist.htm";
											  
	
	//Only true while parsing http://www.nackakickball.com/scheduler/aplsteamlist.htm
	public static final  String LIST_ELEMENT = "<li>";
	public static final  String CLOSE_LIST_ELEMENT = "</ul>";
	public static final  String HR_ELEMENT = "<hr>";
	public static final  String TR_ELEMENT = "<tr>";
	public static final  String CLOSE_TABLE_ELEMENT = "</table>";
	
	/**
	 * Retrieves the HTML code from a given URL
	 * @param someUrl The website to extract the data from
	 * @return The string representation of a webpage
	 * @throws ConnectException if the operation times out a ConnectException will be thrown
	 * @throws IOException if the operation fails to read the data stream from the page an IO exception will be thrown
	 */
	public static String getHtmlFromUrl( String someUrl ) throws ConnectException, IOException
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
		br.close();
		
		//creating the variable to investigate strange exception being thrown on 
		//method exit. if its still hear later, clean it up
		String output = sb.toString();
		return output;
	}
	
	/**
	 * parses the data of the list of data passed from a partially read HTML page
	 * @param htmlList <li> elements of the page's list
	 * @return the data extracted from the raw HTML
	 */
	public static Hashtable<String, TeamElement> ParseTeams(String htmlList) {
		Hashtable<String, TeamElement> teamsFound = new Hashtable<String, TeamElement>();
		
		String[] listElements = htmlList.split("(<li>)");
		
		for( int i = 0; i < listElements.length; i++ )
		{
			String curElement = listElements[i];
			if( curElement.equals(""))
				continue;

			TeamElement element;
			String teamName = "", teamPage = "", teamColor = "";

			String uncleanTeamValues = curElement.split("\".*?\"")[1];
			String teamValues = uncleanTeamValues.substring(1, uncleanTeamValues.indexOf("</a>"));
			
			int openParen = teamValues.indexOf("(");
			int closeParen = teamValues.indexOf(")");
			
			//Get the name
			teamName = (openParen == -1 ) ? teamValues : teamValues.substring(0, teamValues.indexOf("("));
			
			//Get the color
			teamColor = ( openParen == -1 || closeParen == -1 ) ? TShirtColor.UNKNOWN.name : teamValues.substring(openParen+1, closeParen);
			
			//Get the URL
			String uncleanTeamPage = curElement.split("(?<=^|>)[^><]+?(?=<|$)",	Pattern.CASE_INSENSITIVE | Pattern.DOTALL)[0];
			teamPage = uncleanTeamPage.substring( uncleanTeamPage.indexOf("=\"")+2, uncleanTeamPage.indexOf("\">"));

			//clean up results
			teamName = teamName.trim();
			teamPage = teamPage.trim();
			teamColor = teamColor.trim();

			element = new TeamElement( teamName, teamPage, TShirtColor.lookup(teamColor) );
			teamsFound.put(teamName, element);			
		}
		return teamsFound;
	}
	
	/**
	 * Reads in a HTML table row of TDs, returns the matchup data
	 * @param htmlRow the TDs in the table row
	 * @return A ScheduledMatchUp of the matchup data
	 * 
	 */
	public static String[] ExtractMatchupFromTD(String htmlRow) {
		
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
	
	/**
	 * Scrubs webpage for a team's schedule
	 * 
	 * @param teamName Name of the team to lookup
	 * @param teamPageUrl url of the team
	 * @return a list of matchups for the team that represent their schedule
	 */
	public static ArrayList<ScheduledMatchUp> GetTeamSchedule( String teamName, String teamPageUrl )
	{
		ArrayList<ScheduledMatchUp> list = new ArrayList<ScheduledMatchUp>();
		
		String teamScheduleHtml = "";
		try
		{
			teamScheduleHtml = NackaHTML.getHtmlFromUrl( teamPageUrl );
		}
		catch( Exception e )
		{
			Log.e(LOG_TAG, e.getMessage());
			return list;
		}

		String html = teamScheduleHtml.substring(teamScheduleHtml.indexOf(NackaHTML.HR_ELEMENT)); //Truncate Early code
		html = html.substring( html.indexOf(NackaHTML.TR_ELEMENT), html.indexOf(NackaHTML.CLOSE_TABLE_ELEMENT)); //Finish trucating all of the code outside of the table
		
		String[] tableRows = html.split("(</tr>)");
		
		for( int row = 0; row < tableRows.length; row++ )
		{
			String curRow = tableRows[row];
			curRow = curRow.substring(curRow.indexOf("<TD>"));//puts us at the first piece of data
			String[] vals = NackaHTML.ExtractMatchupFromTD( curRow );
			ScheduledMatchUp tempMatchup = new ScheduledMatchUp(teamName, vals[0], vals[1], vals[2], vals[3], vals[4], vals[5]);
			list.add(tempMatchup);			
		}
		return list;
	}
}
