package com.nacka_kickball.Data;

import java.util.Hashtable;
import java.util.concurrent.ExecutionException;

import com.nacka_kickball.Tasks.NackaGetTeamsTask;

import android.content.Context;
import android.content.SharedPreferences;

public class DataStorageManager {
	
	//SharedPreferences file
	private static final String NACKA_PREFS = "NackaPrefsFile", _myteamkey = "nacka_my_team";
	private static Hashtable<String, TeamElement> _teams;
	
	
	
	/** Gets the team registred as "My Team" from the SharedPreferences file
	 * @param c a reference to the current context the application is in
	 * @return the name of the team that is registor, or "" if not set
	 */
	public static String getMyTeam(Context c)
	{
		SharedPreferences settings = c.getSharedPreferences( NACKA_PREFS, 0);
		String team = settings.getString(_myteamkey, "");
		return team;
	}
	
	/**
	 * Registers a team as "My Team" in the SharedPreferences
	 * @param c the current context the application is in
	 * @param newTeam the name of the team to register
	 */
	public static void setMyTeam(Context c, String newTeam )
	{
		SharedPreferences settings = c.getSharedPreferences( NACKA_PREFS, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(_myteamkey, newTeam);
		editor.commit();
	}
	
	
	public static Hashtable<String,TeamElement> getTeams() throws InterruptedException, ExecutionException
	{
		if( _teams == null )
		{
			 _teams = new Hashtable<String, TeamElement>();
		}
		if( _teams.size() == 0)
		{
			_teams = new NackaGetTeamsTask().execute().get();
		}
		return _teams;
	}
	
	public static TeamElement getTeamData( String teamName ) throws InterruptedException, ExecutionException
	{
		return getTeams().get(teamName);
	}
}
