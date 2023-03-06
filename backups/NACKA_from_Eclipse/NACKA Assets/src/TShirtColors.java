import java.awt.Color;

public final class TShirtColors {
	private static final String ANTIQUE_CHERRY_RED ="Antique Cherry Red";
	private static final String ANTIQUE_SAPPHIRE ="Antique Sapphire";
	private static final String ASH ="Ash";
	private static final String BLACK ="Black";
	private static final String CAROLINA_BLUE ="Carolina Blue";
	private static final String CHARCOAL ="Charcoal";
	private static final String DARK_CHOCOLATE ="Dark Chocolate";
	private static final String FOREST_GREEN ="Forest Green";
	private static final String GOLD ="Gold";
	private static final String HELICONIA ="Heliconia";
	private static final String INDIGO_BLUE ="Indigo Blue";
	private static final String IRISH_GREEN ="Irish Green";
	private static final String KIWI ="Kiwi";
	private static final String LIGHT_BLUE ="Light Blue";
	private static final String LIGHT_PINK ="Light Pink";
	private static final String MAROON ="Maroon";
	private static final String MILITARY_GREEN ="Military Green";
	private static final String NAVY ="Navy";
	private static final String ORANGE ="Orange";
	private static final String PAPRIKA ="Paprika";
	private static final String PURPLE ="Purple";
	private static final String RED ="Red";
	private static final String ROYAL_BLUE ="Royal Blue";
	private static final String SAND ="Sand";
	private static final String SPORT_GREY ="Sport Grey";
	private static final String VIOLET ="Violet";
	private static final String WHITE ="White";
	
	public static Color GetColor( String color )
	{
		switch( color )
		{
		case ANTIQUE_CHERRY_RED: 	return new Color(189,16,61);
		case ANTIQUE_SAPPHIRE: 		return new Color(24,132,171);
		case ASH: 					return new Color(203,204,194);
		case BLACK: 				return new Color(8,8,8);
		case CAROLINA_BLUE: 		return new Color(93,147,193);
		case CHARCOAL: 				return new Color(64,69,68);
		case DARK_CHOCOLATE: 		return new Color(54,38,38);
		case FOREST_GREEN: 			return new Color(23,40,0);
		case GOLD: 					return new Color(238,155,18);
		case HELICONIA: 			return new Color(233,147,120);
		case INDIGO_BLUE: 			return new Color(66,95,113);
		case IRISH_GREEN: 			return new Color(0,159,84);
		case KIWI: 					return new Color(122,170,71);
		case LIGHT_BLUE: 			return new Color(164,187,201);
		case LIGHT_PINK: 			return new Color(231,184,204);
		case MAROON: 				return new Color(83,13,11);
		case MILITARY_GREEN: 		return new Color(75,86,38);
		case NAVY: 					return new Color(21,45,61);
		case ORANGE: 				return new Color(222,111,46);
		case PAPRIKA: 				return new Color(227,50,34);
		case PURPLE: 				return new Color(55,23,79);
		case RED: 					return new Color(177,0,0);
		case ROYAL_BLUE: 			return new Color(2,56,133);
		case SAND:	 				return new Color(200,181,149);
		case SPORT_GREY: 			return new Color(134,134,134);
		case VIOLET: 				return new Color(116,106,159);
		case WHITE: 				return new Color(240,240,233);
		default: 					return new Color(255,255,255);			
		}
	}
}
