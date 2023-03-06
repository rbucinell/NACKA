package com.rbucinell.nacka_kickball.Data;

import com.rbucinell.nacka_kickball.*;

/**
 * Denotes the different manin menu items; order of the icons will depend on the ordinal of the values
 *  
 * @author rbucinell
 *
 */
public enum MainMenuItemID {
	
	//The ordinal of this determines the index of the menu item
	//Comment out to disable page
	TEAMS 			(R.drawable.allteams, 			AllTeams.class), //R.drawable.teams
	MY_TEAM			(R.drawable.myschedule,			TeamScheduleActivity.class), //R.drawable.myteam
	
	ANNOUNCEMENTS	(R.drawable.nackanews, 			AnnouncementsActivity.class), //R.drawable.announcements
	RULES			(R.drawable.nackarules, 		RulesActivity.class),
	
	FIELDS			(R.drawable.nackafields,		FieldsActivity.class),//,
	//STANDINGS		(R.drawable.currentstandings, 	StandingsListActivity.class);//R.drawable.standings
	
	//SPLASH			(R.drawable.splash_image, 	SplashActivity.class),
	//FUTURE Features
	//THISWEEK		(R.drawable.curweek,			CurrentWeekActivity.class),
	ABOUT			(R.drawable.aboutapp,			AboutActivity.class);
	
	
	private int _image;
	private Class<?> _activity;
	
	public static final MainMenuItemID values[] = values();
	
	private MainMenuItemID(int image, Class<?> c) {
		_image = image;
		_activity = c;
	}
	
	public int getImage()
	{
		return _image;
	}	
	
	public Class<?> getActivity()
	{
		return _activity;
	}
	
}
