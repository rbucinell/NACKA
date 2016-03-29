var ALL_TEAMS = [];

function Team( name, color, url )
{
	this.Name = name;
	this.ColorName = color;
	this.Color = Colors.GetColorByName(color);
	this.URL = url;
	
	ALL_TEAMS.push( this );
}

Team.prototype.ToString = function()
{
	return this.Name +  ', '+ this.Color + ', '+ this.URL;
}

Team.ExtractNameFromRaw = function( rawName )
{
	return rawName.substring( 0, rawName.indexOf( '(' ) -1 );
}

Team.ExtractColorFromRaw = function( rawName )
{
	return rawName.substring( rawName.indexOf( '(' )+1, rawName.indexOf(')') );
}