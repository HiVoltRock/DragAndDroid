package draganddroid;

import global.Constants;

import java.awt.Color;
import java.awt.Graphics;

public class ATextBox extends AndroidElement {

	public ATextBox(String name, int x, int y) {
		super.name = name;
		super.x = x;
		super.y = y;
		super.height = Constants.ATextBoxWidth;
		super.width = Constants.ATextBoxHeight;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(217,163,151));
		g.fillRect(x, y, Constants.ATextBoxWidth, Constants.ATextBoxHeight);
		g.setColor(Color.BLACK);
		g.drawString(name, x+10, y+20);
	}

	@Override
	public String outputElementXML() {
		String output = "";
		output += "\t<Element type=\"ATextBox\" ";
		output += "\"name\"=" + super.name + " ";
		output += "\"x\"=" + super.x + " ";
		output += "\"y\"=" + super.y + " ";
		output += "\"height\"=" + super.height + " ";
		output += "\"width\"=" + super.width + " ";
		output += "\"caption\"=" + super.caption + ">\n";
		output += "\t</Element>\n";
		return output;
	}

	@Override
	public boolean isInside(int x, int y) {
		for ( int i = this.x; i < this.x+width; i++ ) {
			for ( int j = this.y; j < this.y + height; j++ ) {
				if ( x == i && y == j ) {
					return true;
				}
			}
		}
		return false;
	}
}
