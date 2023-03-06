package com.nacka_kickball;

import com.nacka_kickball.Data.TShirtColor;

public class MatchupListItem {
	public int field_icon, shirt_icon, bgColor;
	public TShirtColor shirtColor;
	public String title;
	
	public MatchupListItem()
	{
		super();
	}
	
	public MatchupListItem( int field_icon, int shirt_icon, String title, int bgColor)
	{
		this.shirt_icon = shirt_icon;
		this.field_icon = field_icon;
		this.title = title;
		this.bgColor = bgColor;
		this.shirtColor = TShirtColor.UNKNOWN;
	}
	
	public MatchupListItem( int field_icon, int shirt_icon, String title, int bgColor, TShirtColor shirtColor)
	{
		this.shirt_icon = shirt_icon;
		this.field_icon = field_icon;
		this.title = title;
		this.bgColor = bgColor;
		this.shirtColor = shirtColor;
	}
}
