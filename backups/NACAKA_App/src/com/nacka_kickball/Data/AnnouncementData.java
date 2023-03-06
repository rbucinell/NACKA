package com.nacka_kickball.Data;

import java.util.ArrayList;

import org.jsoup.nodes.Document;

public class AnnouncementData {

	private ArrayList<NackaNewsItem> newsItems;
	private ArrayList<NackaEventItem> eventItems;
	private Document document;
	
	public AnnouncementData() 
	{
		newsItems = new ArrayList<NackaNewsItem>();
		eventItems = new ArrayList<NackaEventItem>();
		document = null;
	}
	
	public AnnouncementData(ArrayList<NackaNewsItem> newsItems, ArrayList<NackaEventItem> eventItems )
	{
		this.newsItems = newsItems;
		this.eventItems = eventItems;
		document = null;
	}
	
	public AnnouncementData(ArrayList<NackaNewsItem> newsItems, ArrayList<NackaEventItem> eventItems, Document doc )
	{
		this.newsItems = newsItems;
		this.eventItems = eventItems;
		document = doc;
	}
	
	public ArrayList<NackaNewsItem> getNewsItems() {
		return newsItems;
	}

	public void setNewsItems(ArrayList<NackaNewsItem> newsItems) {
		this.newsItems = newsItems;
	}

	public ArrayList<NackaEventItem> getEventItems() {
		return eventItems;
	}

	public void setEventItems(ArrayList<NackaEventItem> eventItems) {
		this.eventItems = eventItems;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
}
