package com.rbucinell.nacka_kickball.Data;

public class NackaEventItem {

	private String title, url;
	
	static final String LOG_TAG = "NACKA_EVENT_ITEM";
	
	public NackaEventItem( String title, String url) 
	{
		this.title = title;
		this.url = url;
	}

	public String getTitle()
	{
		return title;
	}
	
	public String getUrl()
	{
		return url;
	}
}
