package com.rbucinell.nacka_kickball;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rbucinell.nacka_kickball.Tasks.NackaDownloadRules;

public class RulesActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rules);
		
		Button socialButton = (Button)findViewById(R.id.social_rule_dl_button);
		socialButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				new NackaDownloadRules(getApplicationContext()).execute(R.id.social_rule_dl_button);
			}
		});
		
		
		Button competitiveButton = (Button)findViewById(R.id.competitive_rule_dl_button);
		competitiveButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				new NackaDownloadRules(getApplicationContext()).execute(R.id.competitive_rule_dl_button);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rules, menu);
		return true;
	}
}
