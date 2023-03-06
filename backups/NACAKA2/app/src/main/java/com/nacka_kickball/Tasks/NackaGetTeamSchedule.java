package com.nacka_kickball.Tasks;

import java.util.ArrayList;
import com.nacka_kickball.Data.ScheduledMatchUp;
import com.nacka_kickball.Data.TeamElement;
import android.os.AsyncTask;
import android.util.Log;

public class NackaGetTeamSchedule extends AsyncTask<TeamElement, Void, ArrayList<ScheduledMatchUp>> {

	private static final String LOG_TAG = "GET_TEAM_SCHEDULE_TASK";
	
	private String SCHEDULE_URL_ROOT = "http://www.nackakickball.com/scheduler/";
	
	@Override
	protected ArrayList<ScheduledMatchUp> doInBackground(TeamElement... params) {
		
		TeamElement team = params[0];
		String url = SCHEDULE_URL_ROOT + team.getPage();
		ArrayList<ScheduledMatchUp> schedule = NackaHTML.GetTeamSchedule( team.getName(), url );
		
		Log.v(LOG_TAG, schedule.size() + " teams found for [" + team.getName() + "]" );
		return schedule;		
	}

}
