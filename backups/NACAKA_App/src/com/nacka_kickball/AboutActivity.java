package com.nacka_kickball;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class AboutActivity extends Activity {

	String donateLink = "https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=swiftstriker00@gmail.com&lc=US&item_name=The%20NACKA%20App%20is%20pretty%20cool&currency_code=USD&bn=PP%2dDonationsBF";
	String LOG_TAG = "nacka_kickball.AboutActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		// Show the Up button in the action bar.
		setupActionBar();
		
		TextView versionView = (TextView)findViewById(R.id.app_version_id);
		
		String text = "App Version: Error Getting App Version";
		try {
			PackageInfo pInfo;
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			text = "App Version: " + pInfo.versionName;
		} catch (NameNotFoundException e) {
			Log.e(LOG_TAG, "Error getting appversion");
		}
		
		versionView.setText(text);
		
		/*ImageButton ib = (ImageButton) findViewById(R.id.donateButton);
		ib.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(donateLink));				
				AboutActivity.this.startActivity(browserIntent);				
			}
		});*/
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
		getMenuInflater().inflate(R.menu.about, menu);
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
