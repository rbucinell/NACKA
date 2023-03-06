package com.nacka_kickball.Data;

import com.nacka_kickball.R;

import android.graphics.Color;

public enum TShirtColor {
	
	UNKNOWN     ("Unknown",255,255,255),
	RAINBOW     ("Rainbow",255,255,255),
	ANTIQUE_CHERRY_RED     ("Antique Cherry Red",207,11,61),
	ANTIQUE_IRISH_GREEN     ("Antique Irish Green",46,122,75),
	ANTIQUE_JADE_DOME     ("Antique Jade Dome",38,155,164),
	ANTIQUE_ORANGE     ("Antique Orange",220,80,44),
	ANTIQUE_ROYAL     ("Antique Royal",4,50,125),
	ANTIQUE_SAPPHIRE     ("Antique Sapphire",25,128,169),
	ASH     ("Ash",225,227,216),
	AUBERGINE     ("Aubergine",58,32,61),
	AZALEA     ("Azalea",215,130,163),
	BERRY     ("Berry",109,4,63),
	BLACK     ("Black",9,9,9),
	BLACKBERRY     ("Blackberry",18,1,43),
	BLUE_DUSK     ("Blue Dusk",24,48,52),
	BROWN_SAVANA     ("Brown Savana",107,94,78),
	CARDINAL_RED     ("Cardinal Red",118,16,30),
	CAROLINA_BLUE     ("Carolina Blue",90,142,190),
	CHARCOAL     ("Charcoal",65,71,69),
	CHERRY_RED     ("Cherry Red",174,5,0),
	CHESTNUT     ("Chestnut",110,72,59),
	CORAL_SILK     ("Coral Silk",229,101,98),
	DAISY     ("Daisy",227,181,61),
	DARK_CHOCOLATE     ("Dark Chocolate",51,29,31),
	DARK_HEATHER     ("Dark Heather",51,52,46),
	ELECTRIC_GREEN     ("Electric Green",139,177,120),
	FOREST_GREEN     ("Forest Green",15,23,0),
	GALAPAGOS_BLUE     ("Galapagos Blue",0,91,91),
	GARNET     ("Garnet",94,8,19),
	GOLD     ("Gold",223,148,21),
	GRAVEL     ("Gravel",146,145,151),
	HEATHER_CARDINAL     ("Heather Cardinal",91,46,41),
	HEATHER_INDIGO     ("Heather Indigo",66,80,91),
	HEATHER_ROYAL     ("Heather Royal",46,53,71),
	HEATHER_NAVY     ("Heather Navy",46,53,71),
	HELICONIA     ("Heliconia",228,46,120),
	HONEY     ("Honey",229,175,66),
	ICE_GREY     ("Ice Grey",189,194,197),
	INDIGO_BLUE     ("Indigo Blue",59,81,95),
	IRIS     ("Iris",76,88,140),
	IRISH_GREEN     ("Irish Green",7,153,80),
	JADE     ("Jade",12,127,124),
	KELLY_GREEN     ("Kelly Green",6,126,55),
	KIWI     ("Kiwi",121,171,72),
	LIGHT_BLUE     ("Light Blue",163,191,205),
	LIGHT_PINK     ("Light Pink",237,190,208),
	LILAC     ("Lilac",63,6,123),
	LIME     ("Lime",124,204,27),
	MAROON     ("Maroon",77,9,6),
	METRO_BLUE     ("Metro Blue",43,54,76),
	MIDNIGHT     ("Midnight",0,37,43),
	MILITARY_GREEN     ("Military Green",57,63,29),
	NATURAL     ("Natural",240,232,211),
	NAVY     ("Navy",13,29,42),
	OLD_GOLD     ("Old Gold",186,143,64),
	OLIVE     ("Olive",73,64,47),
	ORANGE     ("Orange",224,74,0),
	ORCHID     ("Orchid",197,151,200),
	PISTACHIO     ("Pistachio",164,176,114),
	PRAIRIE_DUST     ("Prairie Dust",100,92,73),
	PURPLE     ("Purple",30,12,52),
	RED     ("Red",174,1,0),
	ROYAL_BLUE     ("Royal Blue",3,55,131),
	RUSTY_BRONZE     ("Rusty Bronze",131,76,56),
	SAFETY_GREEN     ("Safety Green",227,252,37),
	SAFETY_ORANGE     ("Safety Orange",234,113,20),
	SAFETY_PINK     ("Safety Pink",248,108,153),
	SAND     ("Sand",213,189,161),
	SAPPHIRE     ("Sapphire",19,131,169),
	SERENE_GREEN     ("Serene Green",173,189,162),
	SKY     ("Sky",127,193,217),
	SPORT_GREY     ("Sport Grey",138,138,138),
	STONE_BLUE     ("Stone Blue",119,152,169),
	SUNSET     ("Sunset",241,95,54),
	TAN     ("Tan",179,149,89),
	TANGERINE     ("Tangerine",229,104,24),
	TENNESSEE_ORANGE     ("Tennessee Orange",235,108,1),
	TEXAS_ORANGE     ("Texas Orange",170,69,13),
	TROPICAL_BLUE     ("Tropical Blue",14,141,147),
	TURF_GREEN     ("Turf Green",43,103,13),
	VEGAS_GOLD     ("Vegas Gold",224,192,141),
	VIOLET     ("Violet",121,112,169),
	WHITE     ("White",250,250,240),
	YELLOW_HAZE     ("Yellow Haze",223,180,102);
	
	public final String name;
	public final int r;
	public final int g;
	public final int b;
	
	/**
	 * Private Constructor for TShirtColor objects
	 * @param name string representation of the shirt color
	 * @param r r-value
	 * @param g g-value
	 * @param b b-value
	 */
	private TShirtColor( String name, int r, int g, int b )
	{
		this.name = name;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	/**
	 * Displays a string representation of the color in RRGGBB style
	 * @return returns a RRGGBB string of the color
	 */
	public String ColorString()
	{
		String r = ( this.r < 10 ) ? "0" + this.r : "" + this.r;
		String g = ( this.g < 10 ) ? "0" + this.g : "" + this.g;
		String b = ( this.b < 10 ) ? "0" + this.b : "" + this.b;
		return "#"+ r + g + b;
	}
	
	/**
	 * Returns the rgb int of the TShirtColor for use in Views
	 * @return
	 */
	public int getColorInt()
	{
		return Color.rgb( this.r, this.g, this.b );
	}
	
	/**
	 * Look up the TShirtColor by the name of the color
	 * @param colorName string representation of the color (e.g. Antique Cherry)
	 * @return the TShirtColor object
	 */
	public static TShirtColor lookup( String colorName ){
		for( TShirtColor shirt : values()){
			if( shirt.name.equals(colorName))
				return shirt;
		}
		return UNKNOWN;
	}
	
	/**
     * Passing in whether or not the game has been played will produce the light or dark image
     * 
     * @param boolean has the game been played
     * @return the id of the image
     * 
     */
    public static int GetImgIconID( boolean isPlayed) {
    	if( isPlayed )
    		return R.drawable.shirt_hollow_template;
    	else
    		return R.drawable.shirt_hollow_template_light;
	}
}
