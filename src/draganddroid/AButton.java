package draganddroid;

import global.Constants;

import java.awt.Color;
import java.awt.Graphics;

public class AButton extends AndroidElement {
	
	public AButton(String name, int x, int y){
		super.name = name;
		super.x = x;
		super.y = y;
		super.height = 20;
		super.width = 50;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, Constants.AButtonWidth, Constants.AButtonHeight);
		g.setColor(Color.BLACK);
		g.drawString(name, x+10, y+20);
		
	}

	@Override
	public String outputElementXML() {
		String output = "";
		output += "\t<Element type=\"AButton\">\n";
		output += "\t\t<name>" + super.name + "</name>\n";
		output += "\t\t<x>" + super.x + "</x>\n";
		output += "\t\t<y>" + super.y + "</y>\n";
		output += "\t\t<height>" + super.height + "</height>\n";
		output += "\t\t<width>" + super.width + "</width>\n";
		output += "\t\t<caption>" + super.caption + "</caption>\n";
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
