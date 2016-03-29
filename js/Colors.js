var NACKAWEB_BACKGROUND    = "#FFFFFF";
var NACKAWEB_OFFBACKGROUND = "#777777";
var NACKAWEB_HIGHLIGHT     = "#EF1C2B";
var COLOR_PRIMARY          = "#3F51B5";
var COLOR_PRIMARYDARK      = "#303F9F";
var COLOR_ACCENT           = "#FF4081";
var BLACK_OVERLAY          = "#66000000";
var WHITE_BG               = "#F2F2F2";
var NACKA_GRAY_BG          = "#393748";
var NACKA_GRAY_BG_LIGHT    = "#6E7386";
var OVERLAY_GRAY           = "#803D3D3D";

var DEFAULT 			= "#E0FFFF";
var ANTIQUE_CHERRY_RED  = "#BD103D";
var ANTIQUE_IRISH_GREEN = "#39854d";
var ANTIQUE_JADE_DOME   = "#3aa5ab";
var ANTIQUE_ORANGE      = "#db4f2c";
var ANTIQUE_ROYAL       = "#01347a";
var ANTIQUE_SAPPHIRE    = "#1884AB";
var ASH                 = "#cbccc2";
var AZALEA              = "#db7ba0";
var BERRY               = "#73003b";
var BLACK               = "#080808";
var BLACKBERRY          = "#2b1159";
var BLUE_DUSK           = "#142e33";
var BROWN_SAVANA        = "#695d4f";
var CAMO_GREEN          = "#556B2F";
var CARDINAL            = "#78111f";
var CARDINAL_RED        = "#78111f";
var CAROLINA_BLUE       = "#5D93C1";
var CHARCOAL            = "#404544";
var CHERRY_RED          = "#ba0000";
var CHESTNUT            = "#362626";
var CORAL_SILK          = "#e3645d";
var DAISY               = "#ebae2a";
var DARK_CHOCOLATE      = "#362626";
var DARK_HEATHER        = "#434544";
var ELECTRIC_GREEN      = "#5db350";
var FOREST_GREEN        = "#1c2e02";
var GALAPAGOS_BLUE      = "#005d5e";
var GARNET              = "#5c0615";
var GOLD                = "#EE9B12";
var GRAVEL              = "#939296";
var HEATHER_CARDINAL    = "#733938";
var HEATHER_INDIGO      = "#425361";
var HEATHER_NAVY        = "#333947";
var HELICONIA           = "#FF1493";
var HONEY               = "#f2b450";
var ICE_GRAY            = "#c1c6c9";
var INDIGO_BLUE         = "#425F71";
var IRIS                = "#465982";
var IRISH_GREEN         = "#3CB371";
var JADE                = "#067a7a";
var KELLY_GREEN         = "#0d823e";
var KIWI                = "#9ACD32";
var LIGHT_BLUE          = "#AFEEEE";
var LIGHT_PINK          = "#FFB6C1";
var LILAC               = "#440c78";
var LIME                = "#7bcc2b";
var MAROON              = "#53120B";
var METRO_BLUE          = "#2d384d";
var MIDNIGHT            = "#00252b";
var MILITARY_GREEN      = "#556B2F";
var NATURAL             = "#ede5d5";
var NAVY                = "#152D3D";
var OLD_GOLD            = "#b8924d";
var OLIVE               = "#4d4336";
var ORANGE              = "#FF4500";
var ORCHID              = "#c99fcf";
var PISTACHIO           = "#b3bf80";
var PRAIRIE_DUST        = "#69614c";
var PURPLE              = "#4B0082";
var RED                 = "#FF0000";
var ROYAL_BLUE          = "#00008B";
var RUSTY_BRONZE        = "#944732";
var SAFETY_GREEN        = "#e4f500";
var SAFETY_ORANGE       = "#fa6400";
var SAFETY_PINK         = "#fc6592";
var SAND                = "#DEB887";
var SAPPHIRE            = "#1983b0";
var SERENE_GREEN        = "#afbfa3";
var SKY                 = "#7fc1db";
var SPORT_GREY          = "#808080";
var STONE_BLUE          = "#769ead";
var SUNSET              = "#e65632";
var TAN                 = "#b89358";
var TANGERINE           = "#db611a";
var TENNESSEE_ORANGE    = "#ed6700";
var TEXAS_ORANGE        = "#ad4315";
var TROPICAL_BLUE       = "#00949e";
var TURF_GREEN          = "#27690c";
var VEGAS_GOLD          = "#e3c38d";
var VIOLET              = "#9370DB";
var WHITE               = "#FFFFFF";
var YELLOW_HAZE         = "#ebc56e";

function Colors()
{
	
}

Colors.GetColorByName = function( name )
{
	if( name == null || name === 'undefined')
	{
		return name;
	}
	else if( name == "RAINBOW" )
	{
		return name;
	}
	var color = name.toUpperCase().replaceAll(' ', '_' );
	return window[color];
}

String.prototype.replaceAll = function(search, replacement) {
    var target = this;
    return target.replace(new RegExp(search, 'g'), replacement);
};
