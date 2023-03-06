package com.nacka_kickball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SplashActivity extends Activity {

	protected int _wait = 3000; 
	
	
    private Thread splashTread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
		
		splashTread = new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					synchronized (this) {
						wait(_wait);
					}
				}
				catch( InterruptedException e) { }
				finally
				{
					finish();
					
					Intent i = new Intent(getBaseContext(), MainMenuActivity.class );
					startActivity(i);
				}
			}
		};
		splashTread.start();
	}

	
}
