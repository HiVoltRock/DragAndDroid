package draganddroid;

import java.awt.Graphics;

public class ALabel extends AndroidElement {

	public ALabel(String name, int x, int y) {
		super.name = name;
		super.x = x;
		super.y = y;
		super.height = 20;
		super.width = 50;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, 100, 50);
		g.drawString(name, x+10, y+20);	
	}

	@Override
	public String outputElementXML() {
		String output = "";
		output += "\t<Element type=\"ALabel\">\n";
		output += "\t\t<name>" + super.name + "</name>\n";
		output += "\t\t<x>" + super.x + "</x>\n";
		output += "\t\t<y>" + super.y + "</y>\n";
		output += "\t\t<height>" + super.height + "</height>\n";
		output += "\t\t<width>" + super.width + "</width>\n";
		output += "\t\t<caption>" + super.caption + "</caption>\n";
		output += "\t</Element>\n";
		return output;
	}

}
