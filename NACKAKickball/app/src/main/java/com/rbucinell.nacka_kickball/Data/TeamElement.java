package com.rbucinell.nacka_kickball.Data;



@SuppressWarnings("serial")
public class TeamElement implements java.io.Serializable{

	private String _name;
	private TShirtColor _color;
	private String _url;

	public TeamElement(String teamName, String teamPage, TShirtColor teamColor) {
		_name = teamName;
		_url = teamPage;
		_color = teamColor;
	}

	public String getName() {
		return _name;
	}

	public String getPage() {
		return _url;
	}

	public TShirtColor getColor() {
		return _color;
	}
	
	public String toString()
	{
		return _name + " (" + _color.name +  ")[ " + _url + " ]";
	}
}
