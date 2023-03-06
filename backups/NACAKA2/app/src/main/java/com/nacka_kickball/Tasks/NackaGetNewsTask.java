package com.nacka_kickball.Tasks;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.nacka_kickball.R;
import com.nacka_kickball.Data.AnnouncementData;
import com.nacka_kickball.Data.NackaEventItem;
import com.nacka_kickball.Data.NackaNewsItem;

public class NackaGetNewsTask extends AsyncTask<Document, Void, AnnouncementData> {

	private static final String LOG_TAG = "GET_NACKA_NEWS_TASK";
	private static final String url = "http://www.nackakickball.com/";
	
	private static Document doc = null;
	
	private Context context;
	private ProgressDialog dialog;

	/** progress dialog to show user that the backup is processing. */
    /** application context. */
    @Override
    protected void onPreExecute() 
    {
		//dialog.show();
    }
    
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(AnnouncementData result) {
		super.onPostExecute(result);
		//dialog.dismiss();
	}

	public NackaGetNewsTask( Context context, ProgressDialog dialog )
	{
		this.context = context;
		this.dialog = dialog;
	}
	
	protected AnnouncementData doInBackground(Document... documentArgs) 
	{
		AnnouncementData data = new AnnouncementData();
		Document document = documentArgs[0];	
		
		//Collect document 
		doc =  ((document == null) ? loadDocument() : document);
		if( doc == null)
		{
			return data;
		}
		else
		{
			data.setDocument( doc );
		}
		
		//Collect news and events
		String dataType = "";
		try 
		{
			dataType = "news";
			data.setNewsItems(getNewsItemsFromDocument());
			
			dataType = "events";
			data.setEventItems(getEventItemsFromDocument());			
		} 
		catch (Exception e )
		{
			Log.e(LOG_TAG, "Error collecting " + dataType + " from page.\n" + e.getMessage());
		}
		return data;		
	}
	
	///////////PRIVATE METHODS//////////
	
	private ArrayList<NackaNewsItem> getNewsItemsFromDocument()
	{
		ArrayList<NackaNewsItem> newsItems = new ArrayList<NackaNewsItem>();
		
		Element articles = doc.select("#articles").first();			
		Iterator<Element> articleIterator = articles.children().iterator();
		
		while(articleIterator.hasNext())
		{
			//This is the list Item of the article
			Element li_element = articleIterator.next();
			
			//Image Element
			Element image = li_element.child(0);
			String imageSource = image.attr("src");
			imageSource = url + imageSource.substring(imageSource.lastIndexOf("../")+3);
			
			Bitmap img = getBitmap(imageSource);
			
			//panel-overlay Element
			Element panelOverlayElement = li_element.child(1);
			
			//get the title
			String title = panelOverlayElement.child(0).html();
					
			//get the content
			Element contentElement = panelOverlayElement.child(1);
			
			String contentText = contentElement.childNode(0).toString();
			String contentLink = url + contentElement.select("a").first().attr("href");	
			
			//Add the news item once data is collected
			newsItems.add(new NackaNewsItem(title, contentText, contentLink, img));
		}
		
		return newsItems;
	}
	
	private ArrayList<NackaEventItem> getEventItemsFromDocument()
	{
		ArrayList<NackaEventItem> events = new ArrayList<NackaEventItem>();
		
		//Gets the list of all events
		Elements eventElements = doc.select(".events_content tr td:last-child");
		Iterator<Element> eventIter = eventElements.iterator();
		
		while(eventIter.hasNext())
		{
			//This is the list Item of the article
			Element li_element = eventIter.next();
			
			String eventTitle = li_element.select("a").text();
			String eventUrl = li_element.select("a").attr("href");
			events.add(new NackaEventItem(eventTitle, eventUrl));					
		}
		
		return events;
	}
	
	private Document loadDocument()
	{
		try
		{
			Document document = Jsoup.connect(url).get();
			return document;
			
		}
		catch( Exception e )
		{
			Log.e(LOG_TAG, "Error loading document: " + e.getMessage());
		}
		return null;
	}
	
	private Bitmap getBitmap(String imageURL) 
	{		
		Bitmap x;
	    try 
	    {
			HttpURLConnection connection = (HttpURLConnection)new URL(imageURL).openConnection();
			connection.setRequestProperty("User-agent","Mozilla/4.0");
			connection.connect();
			InputStream input = connection.getInputStream();
			x= BitmapFactory.decodeStream(input);
			
			connection.disconnect();
			input.close();
			
			return x;
		} 
	    catch (Exception e)
		{
			Log.e(LOG_TAG, e.getMessage());
			return BitmapFactory.decodeResource(context.getResources(), R.drawable.nacka_news_article_template);
		}
	}


}
