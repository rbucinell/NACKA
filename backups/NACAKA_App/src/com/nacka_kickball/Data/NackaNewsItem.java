package com.nacka_kickball.Data;

import android.graphics.Bitmap;

public class NackaNewsItem {

	static final String LOG_TAG = "NACKA_NEWS_ITEM";
	
	String title, content, url;
	Bitmap image;
	
	///////////CONSTRUCTORS/////////////
	public NackaNewsItem(String title, String content, String url, Bitmap image) {
		this.title = title;
		this.content = content;
		this.url = url;
		this.image = image;
	}

	///////////ACCESSOR / MUTATORS//////
	
	public String getTitle()
	{
		return title;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public Bitmap getImage()
	{
		return image;
	}
	
	public String getURL()
	{
		return url;
	}

}
