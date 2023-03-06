package com.rbucinell.nacka_kickball.Tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.rbucinell.nacka_kickball.R;

//http://eliasbland.wordpress.com/2011/03/11/an-example-of-how-to-run-a-background-task-and-report-progress-in-the-status-bar-using-asynctask-on-android/
//http://developer.android.com/reference/android/os/Environment.html
//http://www.curious-creature.org/2010/08/15/scrollviews-handy-trick/
//http://www.androidhive.info/2012/04/android-downloading-file-by-showing-progress-bar/

public class NackaDownloadRules extends AsyncTask<Integer, String, Void>{

	private static final String LOG_TAG = "DOWNLOAD_RULES_TASK";
	private static final String social_rules_url ="http://www.nackakickball.com/Images_Content/Site1/Files/Pages/NACKA_Kickball_Social_Intermediate_Rules_2014.pdf";
	private static final String competitive_rules_url ="http://www.nackakickball.com/Images_Content/Site1/Files/Pages/NACKA_Kickball_Competitive_Rules_2014.pdf";
	
	/**
	 * Enum for the rules files that can be downloaded
	 * @author rbucinell
	 *
	 */
	private enum RuleFile
	{
		SOCIAL("NACKA_Kickball_Social_Intermediate_Rules_2014.pdf",social_rules_url),
		COMPETITIVE("NACKA_Kickball_Competitive_Rules_2014.pdf", competitive_rules_url);
		
		private String file, url;
		private RuleFile( String file, String url)
		{
			this.file = file;
			this.url = url;
		}
		
		public String getFileName()
		{
			return file;
		}
		
		public String getFileURL()
		{
			return url;
		}
	}
	
	private NotificationManager notificationManager;
	private NotificationCompat.Builder builder;
	private PendingIntent mContentIntent;
	private Intent notificationIntent;
       
    private int NOTIFICATION_ID = 1337;
    
	private Context context;
	public NackaDownloadRules(Context applicationContext) {
		context = applicationContext;
	}
	
	@Override
	protected Void doInBackground(Integer... downloadRequest) {
		
		//SETUP DOWNLOADS
		int requst = downloadRequest[0];
		RuleFile ruleFile = (R.id.social_rule_dl_button == requst) ? RuleFile.SOCIAL : RuleFile.COMPETITIVE;
		File dlFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), ruleFile.getFileName());
		Log.v(LOG_TAG, "Download request for " + ruleFile.getFileName());

		//Setup NotificationManager
		notificationManager = (NotificationManager)  context.getSystemService( Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(NOTIFICATION_ID);
		
		//Create NotificationIntent
		notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.fromFile(dlFile));
		notificationIntent.setDataAndType(Uri.fromFile(dlFile), "application/pdf");
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		mContentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

		//Create Notification
		builder = new NotificationCompat.Builder(context);
		builder.setContentTitle(ruleFile.file)
			.setContentText("Download In Progress")
			.setSmallIcon(R.drawable.ic_nacka_notification)
			.setAutoCancel(true)
			.setContentIntent(mContentIntent)
			.setWhen(System.currentTimeMillis());
		Notification n = builder.build();
		n.flags = Notification.FLAG_AUTO_CANCEL;
		
		updateNotification(100,0);
		
		//Download file
		downloadFile( ruleFile, dlFile );		
		return null;
	}

	private void updateNotification(int max, int cur)
	{
		builder.setProgress(max, cur, false);
		double p = ((cur*1.0)/max)*100;
		int percent = (int) p;
		builder.setContentText(((percent < 10) ? "0"+percent : percent) + "% Download Completed");
		notificationManager.notify(NOTIFICATION_ID, builder.build());
	}
	
	private void notificationAlreadyExists()
	{
		builder.setContentText("File already downloaded!");
        builder.setProgress(100,100,false);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
	}
	
	private void noficiationComplete()
	{
		builder.setContentText("Download complete");
        builder.setProgress(0,0,false);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
	}

	/**
	 * Downloads the file in the background
	 * @param ruleFile Enum information of which file to download
	 */
	private void downloadFile(RuleFile ruleFile, File downloadFile) 
	{
		InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        File file;
        
        //Check if file already exists.
		if( downloadFile.exists() )
			notificationAlreadyExists();
		else
		{
			try 
			{
				URL url = new URL(ruleFile.getFileURL());
				connection = (HttpURLConnection) url.openConnection();
				connection.connect();
				
				input =new BufferedInputStream(url.openStream(), 8192);
				int lenghtOfFile = connection.getContentLength();
				
				file = downloadFile;
				output = new FileOutputStream(file );
				
				byte data[] = new byte[1024];			 
	            long total = 0;
	            int count = 0;
	            
	            //Initialize the notification
	            updateNotification(lenghtOfFile,0);
	            
	            //Download the file
				while( (count = input.read(data)) != -1 )
				{
					total += count;
					publishProgress(""+(int)((total*100)/lenghtOfFile));
					output.write(data,0,count);
					updateNotification( lenghtOfFile, (int)total);
				}
				output.flush();	
				
				//Finish the notification
				noficiationComplete();
				Log.v(LOG_TAG, "Download of " + downloadFile.getName() + " completed");
				
				output.close();
				input.close();	
			} 
			catch (Exception e) {
				Log.e(LOG_TAG, "Error in downloadFile()\n"+ e.getMessage());
			}		
		}
	}
}
