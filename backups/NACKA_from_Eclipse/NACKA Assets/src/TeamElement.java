import java.awt.Color;

public class TeamElement {

	private String _name;
	private String _cName;
	private Color _color;
	private String _url;

	public TeamElement(String teamName, String teamPage, String teamColor) {
		_name = teamName;
		_url = teamPage;
		_cName = teamColor;
		_color = TShirtColors.GetColor(_cName);
	}

	public String getName() {
		return _name;
	}

	public String getPage() {
		return _url;
	}

	public Color getColor() {
		return _color;
	}
	
	public String toString()
	{
		return _name + " (" + _cName +  ")[ " + _url + " ]";
	}
}
