package com.rbucinell.nacka_kickball;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class FieldsActivity extends Activity {

	private final String LOG_TAG = "ALL_TEAMS_ACTIVITY";
	final String _url = "https://www.google.com/maps/place/Sahlen's+Stadium/@43.162067,-77.629566,17z/data=!3m1!4b1!4m2!3m1!1s0x0:0x166011261db112af";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fields);
		setupActionBar();
	}
	
	/**
	 * Opens up a link to google map's location of Sahlen's Stadium
	 * @param v the View that called this method ( mapButton )
	 */
	public void onMapsButtonClick(View v) {
		Log.i(LOG_TAG, "Calling google maps url for Sahlen Stadium");
		Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(_url)); 
		startActivity( intent );
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
		getMenuInflater().inflate(R.menu.fields,menu);
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
