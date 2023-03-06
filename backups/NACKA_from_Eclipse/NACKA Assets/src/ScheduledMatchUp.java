import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScheduledMatchUp {
	
	private String _team;
	private Date _datetime;
	private String _location;
	private String _vs;
	private String _opponent;
	private String _result;
	
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
		}catch( ParseException pe )
		{
			_datetime = new Date( 0 );
		}
		
		_location = loc;
		_vs = vs.equals("") ? "vs" : vs;
		_opponent = oponent;
		_result = results;
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

	
	public String toString()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		String date = dateFormat.format( _datetime );
		
		DateFormat timeFormat =  new SimpleDateFormat( "hh:mm aa");
		String time = timeFormat.format( _datetime );
		
		
		if( _datetime.after(new Date()))
			return date + " " + time + ": " + _team + " [" + _vs  + "] " + " " + _opponent + " on " + _location;
		else
			return date + " " + time + ": " + _team + " [" + _vs  + "] " + " " + _opponent + " resulted in " + _result;
	}
}
