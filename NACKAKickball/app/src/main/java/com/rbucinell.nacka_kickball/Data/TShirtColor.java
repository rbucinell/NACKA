package com.rbucinell.nacka_kickball.Data;

import com.rbucinell.nacka_kickball.R;

import android.graphics.Color;

public enum TShirtColor {
	
	UNKNOWN     ("Unknown",255,255,255),
	RAINBOW     ("Rainbow",255,255,255),
	ANTIQUE_CHERRY_RED      ("Antique Cherry Red",189,16,61),
	ANTIQUE_IRISH_GREEN     ("Antique Irish Green",57,133,77),
	ANTIQUE_JADE_DOME       ("Antique Jade Dome",58,165,171),
	ANTIQUE_ORANGE  		("Antique Orange",219,79,44),
	ANTIQUE_ROYAL   		("Antique Royal",1,52,122),
	ANTIQUE_SAPPHIRE        ("Antique Sapphire",24,132,171),
	ASH     				("Ash",203,204,194),
	AZALEA  				("Azalea",219,123,160),
	BERRY   				("Berry",115,0,59),
	BLACK   				("Black",8,8,8),
	BLACKBERRY      		("Blackberry",43,17,89),
	BLUE_DUSK       		("Blue Dusk",20,46,51),
	BROWN_SAVANA    		("Brown Savana",105,93,79),
	CAMO_GREEN      		("Camo Green",85,107,47),
	CARDINAL        		("Cardinal",120,17,31),
	CARDINAL_RED    		("Cardinal Red",120,17,31),
	CAROLINA_BLUE   		("Carolina Blue",93,147,193),
	CHARCOAL        		("Charcoal",64,69,68),
	CHERRY_RED      		("Cherry Red",186,0,0),
	CHESTNUT        		("Chestnut",54,38,38),
	CORAL_SILK      		("Coral Silk",227,100,93),
	DAISY   				("Daisy",235,174,42),
	DARK_CHOCOLATE  		("Dark Chocolate",54,38,38),
	DARK_HEATHER    		("Dark Heather",67,69,68),
	ELECTRIC_GREEN  		("Electric Green",93,179,80),
	FOREST_GREEN    		("Forest Green",28,46,2),
	GALAPAGOS_BLUE  		("Galapagos Blue",0,93,94),
	GARNET  				("Garnet",92,6,21),
	GOLD    				("Gold",238,155,18),
	GRAVEL  				("Gravel",147,146,150),
	HEATHER_CARDINAL        ("Heather Cardinal",115,57,56),
	HEATHER_INDIGO  		("Heather Indigo",66,83,97),
	HEATHER_NAVY    		("Heather Navy",51,57,71),
	HELICONIA       		("Heliconia",255,20,147),
	HONEY   				("Honey",242,180,80),
	ICE_GRAY       			("Ice Gray",193,198,201),
	INDIGO_BLUE     		("Indigo Blue",66,95,113),
	IRIS   					("Iris",70,89,130),
	IRISH_GREEN     		("Irish Green",60,179,113),
	JADE    				("Jade",6,122,122),
	KELLY_GREEN     		("Kelly Green",13,130,62),
	KIWI    				("Kiwi",154,205,50),
	LIGHT_BLUE      		("Light Blue",175,238,238),
	LIGHT_PINK      		("Light Pink",255,182,193),
	LILAC   				("Lilac",68,12,120),
	LIME    				("Lime",123,204,43),
	MAROON  				("Maroon",83,18,11),
	METRO_BLUE      		("Metro Blue",45,56,77),
	MIDNIGHT        		("Midnight",0,37,43),
	MILITARY_GREEN  		("Military Green",85,107,47),
	NATURAL 				("Natural",237,229,213),
	NAVY    				("Navy",21,45,61),
	OLD_GOLD        		("Old Gold",184,146,77),
	OLIVE   				("Olive",77,67,54),
	ORANGE  				("Orange",255,69,0),
	ORCHID  				("Orchid",201,159,207),
	PISTACHIO			 	("Pistachio",179,191,128),
	PRAIRIE_DUST    		("Prairie Dust",105,97,76),
	PURPLE  				("Purple",75,0,130),
	RED     				("Red",255,0,0),
	ROYAL_BLUE      		("Royal Blue",0,0,139),
	RUSTY_BRONZE    		("Rusty Bronze",148,71,50),
	SAFETY_GREEN    		("Safety Green",228,245,0),
	SAFETY_ORANGE   		("Safety Orange",250,100,0),
	SAFETY_PINK     		("Safety Pink",252,101,146),
	SAND    				("Sand",222,184,135),
	SAPPHIRE        		("Sapphire",25,131,176),
	SERENE_GREEN    		("Serene Green",175,191,163),
	SKY    					("Sky",127,193,219),
	SPORT_GREY      		("Sport Grey",128,128,128),
	STONE_BLUE      		("Stone Blue",118,158,173),
	SUNSET  				("Sunset",230,86,50),
	TAN     				("Tan",184,147,88),
	TANGERINE       		("Tangerine",219,97,26),
	TENNESSEE_ORANGE        ("Tennessee Orange",237,103,0),
	TEXAS_ORANGE    		("Texas Orange",173,67,21),
	TROPICAL_BLUE   		("Tropical Blue",0,148,158),
	TURF_GREEN      		("Turf Green",39,105,12),
	VEGAS_GOLD      		("Vegas Gold",227,195,141),
	VIOLET  				("Violet",147,112,219),
	WHITE   				("White",255,255,255),
	YELLOW_HAZE     		("Yellow Haze",235,197,110);

	/** C#code to generate these values based on colors.xml
	 class Program
	 {
	 static void Main(string[] args)
	 {
	 XmlDocument doc = new XmlDocument();
	 doc.Load(@"C:\Users\Ryan\Desktop\colors.xml");
	 foreach( XmlNode node in doc.SelectNodes("/colors/color") )
	 {
	 string name = node.Attributes[0].Value.ToString();
	 StringBuilder sb = new StringBuilder(name);
	 sb.Append("\t\t\t(\"");
	 string[] words = name.Split('_');

	 for(int i = 0; i < words.Length;i++)
	 {
	 string word = words[i];
	 word = word.Substring(0, 1).ToUpper() + word.Substring(1).ToLower();
	 sb.Append( word );
	 if ( i + 1 != words.Length )
	 {
	 sb.Append(' ');
	 }

	 }
	 sb.Append("\",");

	 string str_color = node.InnerText;
	 System.Drawing.Color c = System.Drawing.ColorTranslator.FromHtml(str_color);
	 string remaining = "{0},{1},{2}),";

	 sb.Append(String.Format(remaining, c.R, c.G, c.B));



	 Console.WriteLine(sb);
	 }

	 Console.ReadLine();
	 }
	 }
	 */


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
     * @param isPlayed has the game been played
     * @return the id of the image
     * 
     */
    public static int GetImgIconID( boolean isPlayed ) {
    	if( isPlayed )
		{
			return R.drawable.shirt_hollow_template;
		}
    	else {
			return R.drawable.shirt_hollow_template_light;
		}
	}
}
