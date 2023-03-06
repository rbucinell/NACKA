package com.nacka_kickball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import com.nacka_kickball.Data.DataStorageManager;
import com.nacka_kickball.Data.TeamElement;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.support.v4.app.NavUtils;
/*
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
*/

public class AllTeams extends Activity {

	private final String LOG_TAG = "ALL_TEAMS_ACTIVITY";
	private String[] teams = {};
	//private AdView mAdView;
	//private static final String AD_UNIT_ID = "pub-4139730947155162";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_teams);
		
		
		// Show the Up button in the action bar.
		setupActionBar();
		
		ListView lv = (ListView)findViewById(R.id.teamList);
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,teams ));
		try 
		{				
			ArrayList<String> teamNames =  new ArrayList<String>();
			
			//Collect all the team names from the Hashtable
			Iterator<String> iterator = DataStorageManager.getTeams().keySet().iterator();
			
			//read all the team data from the hashtable
			String myTeamPref = DataStorageManager.getMyTeam(this);
			while( iterator.hasNext() )
			{
				String curName = (String) iterator.next();
				if( curName.equals(myTeamPref))
					continue;
				if( curName != null )
					teamNames.add(curName);
			}
			
			//Sort the teams alphabetically, placing registered team at the top
			Collections.sort( teamNames, new SortIgnoreCase()  );
			if( myTeamPref != null && !myTeamPref.equals(""))
				teamNames.add(0, myTeamPref);
			
			//Add the list of team names found to the ListView
			teams = teamNames.toArray(new String[teamNames.size()]);
			lv.setAdapter(new ArrayAdapter<String>( getApplicationContext(), android.R.layout.simple_list_item_1, teams));

			//Add a click listener to each item. This listener will open a new activity with the team name in the intent
			lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
					TextView textView = (TextView)view;
					String s = textView.getText().toString();
					try 
					{
						TeamElement teamData = DataStorageManager.getTeamData(s);					
						Intent intent = new Intent( getBaseContext(), TeamScheduleActivity.class );
						intent.putExtra(getString(R.string.team_element_exta), teamData);
						startActivity( intent );						
					} 
					catch (Exception e) {
						Log.e(LOG_TAG, e.getMessage() + "\nError opening a Team Page");
						Toast toast = Toast.makeText(getApplicationContext(), "Error retreiving team", Toast.LENGTH_LONG);
						toast.show();
					}
					
				}				
			});
			
			lv.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View v, int position, long id){
					
					//Get the view we are interacting with
					TextView textView = (TextView)v;
					String s = textView.getText().toString();
					
					//get context
					Context context = getApplicationContext();
					
					//Set the team
					DataStorageManager.setMyTeam(context, s);
					
					//Tell user!
					CharSequence cs = "My Team: " + DataStorageManager.getMyTeam(context) + " has been registered!";					
					Toast toast = Toast.makeText(context, cs, Toast.LENGTH_LONG);
					toast.show();

					//UpdateList
					reorderList(position);					
					((ListView)v.getParent()).invalidateViews();
					
					return true;
				}				
			}); 
			
		} catch (Exception e){
			//If we find an error. Just display the error as the one and only element in the list
			Log.e(LOG_TAG, e.getMessage());
			Toast toast = Toast.makeText(getApplicationContext(), "Error Loading Teams", Toast.LENGTH_LONG);
			toast.show();
			lv.setAdapter(new ArrayAdapter<String>( getApplicationContext(), android.R.layout.simple_list_item_1, new String[]{ "Error Occured Loading Teams" , e.getMessage() }));
			lv.setOnItemLongClickListener(null);
			lv.setOnItemClickListener(null);
		}
		/*
		mAdView = new AdView(this);
		mAdView.setAdSize(AdSize.BANNER);
		mAdView.setAdUnitId(AD_UNIT_ID);
	    
	    lv.addView(mAdView);

	    AdRequest adRequest = new AdRequest.Builder()
	    	.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	    	.addTestDevice("353092053642765")
	    	.build();
	    // Start loading the ad in the background.
	    mAdView.loadAd(adRequest);*/
	}

	private void reorderList(int position) {
		String replacement = teams[position];
		String cur = "";
		for( int i = 0; i <= position; i++)
		{
			cur = teams[i];
			teams[i] = replacement;
			replacement = cur;
		}
		
	}
	

	
	
	/**
	 * Small Comparator override to ignore case when sorting teams
	 * @author Ryan Bucinell
	 *
	 */
	public class SortIgnoreCase implements Comparator<Object>
	{
		public int compare(Object o1, Object o2)
		{
			String s1 = ( String ) o1;
			String s2 = ( String ) o2;
			return s1.toLowerCase().compareTo(s2.toLowerCase());
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_teams, menu);
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
