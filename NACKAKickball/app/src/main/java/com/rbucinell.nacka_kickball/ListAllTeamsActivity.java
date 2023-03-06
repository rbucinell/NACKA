package com.rbucinell.nacka_kickball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rbucinell.nacka_kickball.Data.DataStorageManager;
import com.rbucinell.nacka_kickball.Data.TeamElement;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
@Deprecated
public class ListAllTeamsActivity extends Activity {

	@Deprecated
	private final String LOG_TAG = "LIST_ALL_TEAMS_ACTIVITY";
	
	@Deprecated
	private String[] teamsInList;
	@Deprecated
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);
		setupActionBar();
		
		ListView lv = (ListView)findViewById(R.id.teamList);	
		
		try 
		{				
			ArrayList<String> teamNames =  new ArrayList<String>();
			
			//Collect all the team names from the Hashtable
			Iterator<String> iterator = DataStorageManager.getTeams().keySet().iterator();
			
			//We are simply going to read all the team data from the hashtable and sort the names
			//Easter egg code added: Joe's team will always show up first.
			//String joes_team = "";
			String myTeamPref = DataStorageManager.getMyTeam(this);
			while( iterator.hasNext() )
			{
				String curName = (String) iterator.next();
				//if( curName.contains(getString(R.string.joes_team_name_indicator)))
				//{
				//	joes_team = curName;
				//	continue;
				//}
				if( curName.equals(myTeamPref))
				{
					continue;
				}
				if( curName != null )
					teamNames.add(curName);
			}
			Collections.sort( teamNames, new SortIgnoreCase()  );
			
			if( myTeamPref != null && !myTeamPref.equals(""))
				teamNames.add(0, myTeamPref);	
			//Add the list of team names found to the ListView
			teamsInList = teamNames.toArray(new String[teamNames.size()]);
			lv.setAdapter(new ArrayAdapter<String>( getApplicationContext(), android.R.layout.simple_list_item_1, teamsInList));

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
	}
	@Deprecated
	private void reorderList(int position) {
		String replacement = teamsInList[position];
		String cur = "";
		for( int i = 0; i <= position; i++)
		{
			cur = teamsInList[i];
			teamsInList[i] = replacement;
			replacement = cur;
			
		}
		
	}
	
	/**
	 * Small Comparator override to ignore case when sorting teams
	 * @author Ryan Bucinell
	 *
	 */
	@Deprecated
	private class SortIgnoreCase implements Comparator<Object>
	{
		public int compare(Object o1, Object o2)
		{
			String s1 = ( String ) o1;
			String s2 = ( String ) o2;
			return s1.toLowerCase().compareTo(s2.toLowerCase());
		}
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@Deprecated
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		getActionBar().hide();
	}
	
	
}
