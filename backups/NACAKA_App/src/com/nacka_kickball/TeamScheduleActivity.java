package com.nacka_kickball;

import com.nacka_kickball.R;
import com.nacka_kickball.Data.*;
import com.nacka_kickball.Tasks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;


public class TeamScheduleActivity extends Activity {

	private String LOG_TAG = "TEAM_SCHEDULE_ACTIVITY";
	private TeamElement _teamElement;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_schedule);
		setupActionBar();
		
		//TextView tv = (TextView) findViewById(R.id.TeamNameTitle);

		// Get the list of matches for the given team
		ArrayList<ScheduledMatchUp> schedule;
		try 
		{
			_teamElement = GetTeamElement();
			//We couldn't find a team in the request or saved preferences, so go make them choose
			//TODO: Make this a fragment some day and return back to this screen?
			if( _teamElement == null)
			{
				Toast toast = Toast.makeText(getApplicationContext(), "No team set, Please register one.", Toast.LENGTH_SHORT);
				toast.show();

				Intent i = new Intent(this, AllTeams.class);
				startActivity(i);
				return;
			}		
			
			//Set title to team's name
			setTitle(_teamElement.getName() + "'s schedule: ");	

			//Create the task to get the team's list of match ups
			schedule = new NackaGetTeamSchedule().execute(_teamElement).get();
			MatchupListItem matchupData[] = new MatchupListItem[ schedule.size() ];

			List<MatchupListItem> mList = new ArrayList<MatchupListItem>();
			for( ScheduledMatchUp match : schedule)
			{
				boolean played = match.isPlayed();
				int back_color = played ? R.color.nacka_gray_bg : R.color.nacka_gray_bg_light;
				MatchupListItem mli = new MatchupListItem( 
						GetFieldIconFromName(match.getLocation()), 
						TShirtColor.GetImgIconID(played), 
						match.toString(),
						getResources().getColor(back_color),
						TShirtColor.lookup(match.opponenetColor())						
						);				
				mList.add(mli);
			}
			matchupData = mList.toArray(matchupData);

			//Add the list of matches to the ListView
			MatchupAdapter adapter = new MatchupAdapter(this, R.layout.listview_matchup_item, matchupData);
			ListView listView = (ListView)findViewById(R.id.TeamMatchesList);
			listView.setAdapter(adapter);

		} 
		catch (Exception e) 
		{
			Log.e(LOG_TAG,e.getMessage());
			Toast toast = Toast.makeText(getApplicationContext(), "Error loading Team's schedule", Toast.LENGTH_LONG);
			toast.show();
		}
	}

	private TeamElement GetTeamElement() throws InterruptedException, ExecutionException {
		Bundle intentExtras = getIntent().getExtras();

		//If there are extras we are coming from the list, if there isn't, we are requesting our fav team
		if( intentExtras == null )
		{
			String myTeam = DataStorageManager.getMyTeam(this);
			if( myTeam == null || myTeam.isEmpty() )
			{
				return null;
			}
			return DataStorageManager.getTeamData(myTeam);
		}
		return (TeamElement) getIntent().getExtras().getSerializable(getString(R.string.team_element_exta));
	}

	/*
	private void SetTextViewColorsBasedOnShirtColor( TextView tv )
	{

		tv.setTextColor(_teamElement.getColor().getColorInt());

		
		//tv.setBackgroundColor(Color.rgb(39, 37, 48)); // Temporary override
		////This code is in place to determine luminance if i want to use later, until then we just use back color = black
		//TShirtColor color = _teamElement.getColor();
		//int d = 0;

        //// Counting the perceptive luminance - human eye favors green color... 
        //double a = 1 - ( 0.299 * color.r + 0.587 * color.g + 0.114 * color.b)/255;
		//
        //if (a < 0.3)
        //	tv.setBackgroundColor(Color.BLACK);
        //else
        //    tv.setBackgroundColor(Color.WHITE);
	}*/

	/**
	 * Reads a string of the field location from the parsed webpage and retrieves the id for the associated icon. 
	 * @param location field location
	 * @return the int representation of a field icon
	 */
	private int GetFieldIconFromName( String location )
	{
		if( location.endsWith("1"))
			return R.drawable.field1;
		else if( location.endsWith("2"))
			return R.drawable.field2;
		else if( location.endsWith("3"))
			return R.drawable.field3;
		return R.drawable.field4;
	}

	public static ArrayList<ScheduledMatchUp> GetTeamSchedule(String teamName,
			String teamPageUrl) {
		ArrayList<ScheduledMatchUp> list = new ArrayList<ScheduledMatchUp>();

		String teamScheduleHtml = "";
		try {
			teamScheduleHtml = NackaHTML.getHtmlFromUrl(teamPageUrl);
		} catch (Exception e) {
			return list;
		}

		// TODO parsing here
		String html = teamScheduleHtml.substring(teamScheduleHtml
				.indexOf(NackaHTML.HR_ELEMENT)); // Truncate Early code
		html = html.substring(html.indexOf(NackaHTML.TR_ELEMENT),
				html.indexOf(NackaHTML.CLOSE_TABLE_ELEMENT)); // Finish
		// trucating all
		// of the code
		// outside of
		// the table

		String[] tableRows = html.split("(</tr>)");

		for (int row = 0; row < tableRows.length; row++) {
			String curRow = tableRows[row];
			curRow = curRow.substring(curRow.indexOf("<TD>"));// puts us at the
			// first piece
			// of data
			String[] vals = NackaHTML.ExtractMatchupFromTD(curRow);
			ScheduledMatchUp tempMatchup = new ScheduledMatchUp(teamName,
					vals[0], vals[1], vals[2], vals[3], vals[4], vals[5]);
			list.add(tempMatchup);
		}
		return list;
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		/*
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		getActionBar().hide();*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.team_schedule, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent( this.getBaseContext(), MainMenuActivity.class));
			//NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
