package draganddroid;

import global.Constants;

import java.awt.Color;
import java.awt.Graphics;

public class ALabel extends AndroidElement {

	public ALabel(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.height = Constants.ALabelWidth;
		this.width = Constants.ALabelHeight;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(157,211,207));
		g.fillRect(x, y, Constants.ALabelWidth, Constants.ALabelHeight);
		g.setColor(Color.BLACK);
		g.drawString(name, x+10, y+20);	
	}

	@Override
	public String outputElementXML() {
		String output = "";
		output += "\t<Element type=\"ALabel\" ";
		output += "name=\"" + this.name + "\" ";
		output += "x=\"" + this.x + "\" ";
		output += "y=\"" + this.y + "\" ";
		output += "height=\"" + this.height + "\" ";
		output += "width=\"" + this.width + "\" ";
		output += "caption=\"" + this.caption + "\">\n";
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

    @Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setX(int x) {
		this.x = x;	
	}

	@Override
	public void setY(int y) {
		this.y = y;	
	}

}
