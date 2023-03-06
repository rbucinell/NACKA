package com.nacka_kickball.Tasks;

import java.util.Hashtable;
import com.nacka_kickball.Data.TeamElement;
import android.os.AsyncTask;
import android.util.Log;

public class NackaGetTeamsTask extends AsyncTask<Void, Void, Hashtable<String, TeamElement>>
{
	private static final String LOG_TAG = "GET_TEAMS_TASK";
	
	@Override
	protected Hashtable<String, TeamElement> doInBackground(Void... params) {
		//Gets the html page
		String allTeamsHtml = Get_AllTeams_HTMLPage();
		
		//Remove all the HMTL until the list elements are left in the page
		allTeamsHtml = allTeamsHtml.substring(allTeamsHtml.indexOf(NackaHTML.LIST_ELEMENT),allTeamsHtml.indexOf(NackaHTML.CLOSE_LIST_ELEMENT));
		
		//Parse the list, and return the table
		return NackaHTML.ParseTeams( allTeamsHtml );
	}
	
	/**
	 * Gets the HTML from the current all team's schedule list
	 * @return a string of the HTML code
	 */
	private static String Get_AllTeams_HTMLPage()
	{
		//Breaking this up the long way because some exception is getting caught yet debugger is
		//saying 'e' is null. Need to investigate future
		String s = "";
		try
		{
			s = NackaHTML.getHtmlFromUrl(NackaHTML.ALL_TEAMS_URL );
		}
		catch( Exception e )
		{
			Log.e(LOG_TAG,e.getMessage());
		}
		return s;
	}
	
}
