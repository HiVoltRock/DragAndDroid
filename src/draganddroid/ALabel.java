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
	public String outputElement() {
		String output = "";
		
		output += "ALabel\n";
		output += super.name + "\n";
		output += super.x + " " + super.y + " " + super.height + " " + super.width + "\n";
		output += super.caption + "\n";
		
		return output;
	}

}
