package com.rbucinell.nacka_kickball;

import java.util.concurrent.ExecutionException;

import org.jsoup.nodes.Document;

import com.rbucinell.nacka_kickball.Data.AnnouncementData;
import com.rbucinell.nacka_kickball.Data.NackaEventItem;
import com.rbucinell.nacka_kickball.Data.NackaNewsItem;
import com.rbucinell.nacka_kickball.Tasks.NackaGetNewsTask;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class AnnouncementsActivity extends Activity {

	private final String LOG_TAG = "ANNOUNCEMENTS_ACTIVITY";
	private NackaEventItem[] events = {new NackaEventItem("Events coming Soon!", "")};//{"test1", "test2", "test3"};
	private NackaNewsItem[] news = new NackaNewsItem[0];

	private static AnnouncementData announcements = null;
	private static Document document;
	
	private ProgressDialog dialog;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_announcements);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//ListView upcomingEventsListView = (ListView)findViewById(R.id.upcoming_events_lv);
		ListView nackaNewsListView = (ListView)findViewById(R.id.news_lv);
		
		try 
		{
			if( announcements == null )
			{
				NackaGetNewsTask getNewsTask = new NackaGetNewsTask( this.getBaseContext(), dialog );
				announcements = getNewsTask.execute(document).get();
				document = announcements.getDocument();
			}
			//dialog = new ProgressDialog( this );
			//dialog.setTitle("Loading News");
	    	//dialog.setMessage("Getting latest news from NACKA website");
	    	//dialog.show();
			
			
			
						
			//News//////////
			int newsCount = announcements.getNewsItems().size();
			news = new NackaNewsItem[ newsCount ];
			for( int i = 0; i < newsCount; i++ )
			{
				news[i] = announcements.getNewsItems().get(i);
			}
			
			//Events////////
			int eventCount = announcements.getEventItems().size();
			if( eventCount > 0 ) //If we don't find any, leave the default coming soon
			{
				events = new NackaEventItem[ eventCount ];
				for( int i = 0; i < eventCount; i++ )
				{
					events[i] = announcements.getEventItems().get(i);
				}
			}			
		} 
		catch (InterruptedException e) 
		{
			Log.e(LOG_TAG, e.getMessage());
		} 
		catch (ExecutionException e) 
		{
			Log.e(LOG_TAG, e.getMessage());
		}
		
		//int counter = 0;
		//for( String item : events )
		//{
		//	events[ counter++ ] = item;
		//}
		//upcomingEventsListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,events));
		NewsAdapter adapter = new NewsAdapter( this, R.layout.listview_nacka_news_item, news);
		nackaNewsListView.setAdapter(adapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		//getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.announcements, menu);
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
