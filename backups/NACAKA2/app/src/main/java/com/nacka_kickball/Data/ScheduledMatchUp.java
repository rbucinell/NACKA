package com.nacka_kickball.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat")
public class ScheduledMatchUp {
	
	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");	
	private static final DateFormat timeFormat =  new SimpleDateFormat( "hh:mm aa");
	
	private String _team;
	private Date _datetime;
	private String _location;
	private String _vs;
	private String _opponent;
	private String _result;
	private boolean _isPlayed;
	
	/**
	 * Constructor for a scheduled match-up for a team on a given date and time
	 * 
	 * @param team The team the match is for
	 * @param date The date that the game will be played on
	 * @param time The time of the game
	 * @param loc The Field that game will be played on
	 * @param vs VS or AT depending on Home or Away
	 * @param oponent The opponent that will be played
	 * @param results If the game has played, entered the win/loss result
	 */
	
	public ScheduledMatchUp( String team, String date, String time, String loc, String vs, String oponent, String results )
	{
		_team = team;
		
		//Parse the string for the date
		String dateString = date + time;
		DateFormat format =  new SimpleDateFormat("EEEE, MMMM dd, yyyyhh:mm aa");
		try
		{
			_datetime = format.parse( dateString );
		}
		catch( ParseException pe )
		{
			_datetime = new Date( 0 );
		}
		_isPlayed = determineIfGameIsPlayed(_datetime);
		_location = loc;
		_vs = vs.equals("") ? "vs" : vs;
		_opponent = oponent;
		
		_result = results;
	}

	private boolean determineIfGameIsPlayed(Date gameDate) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date()); 
		c.add(Calendar.DATE, -1);
		return gameDate.before(c.getTime() );
	}

	/**
	 * @return the Team's name
	 */
	public String getTeam() {
		return _team;
	}

	/**
	 * @return the DateTime object of the date and time of the game
	 */
	public Date getDatetime() {
		return _datetime;
	}

	/**
	 * @return the The field the game will be played on
	 */
	public String getLocation() {
		return _location;
	}

	/**
	 * @return the VS or AT depending on weather the team is home or away
	 */
	public String getVs() {
		return _vs;
	}

	/**
	 * @return the team's opponent
	 */
	public String getOpponent() {
		return _opponent;
	}

	/**
	 * @return the result of the game if it already has concluded
	 */
	public String getResult() {
		return _result;
	}

	public boolean isPlayed()
	{
		return _isPlayed;
	}
	
	public String pastMatchToString()
	{
		String date = dateFormat.format( _datetime );
		return opponentNameWithoutColor() + "\n" + displayResult() + " on " + date;
	}
	
	
	public String futureMatchToString()
	{
		String date = dateFormat.format( _datetime );
		String time = timeFormat.format( _datetime );
		
		return date + "  " + time + " [" + _vs  + "] \n" + opponentNameWithoutColor();// + " on " + _location;
	}
	

	public String opponentNameWithoutColor()
	{
		if( _opponent.indexOf("(") == -1 )
			return _opponent;
		return _opponent.substring(0, _opponent.indexOf("("));
	}
	
	public String opponenetColor()
	{
		int openParen 	= _opponent.indexOf("(");
		int closeParen 	= _opponent.indexOf(")");
		if( openParen == -1 || closeParen == -1 )
			return "Unkown";		
		return _opponent.substring(openParen+1, closeParen);
	}
	
	/**
	 * Returns the results of the scheduled match-up.
	 * If the site hasn't been updated, just say we are waiting
	 * @return the result of the match
	 */
	private String displayResult()
	{
		if( _result == null || _result.equals("") )
			return "Waiting for results...";
		return _result;		
	}
	
	public String toString()
	{		
		return _isPlayed ? pastMatchToString() : futureMatchToString() ;
	}
}
