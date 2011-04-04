package draganddroid;

import java.awt.Graphics;

public class ALabel extends AndroidElement {

	public ALabel() {
		
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String outputElement() {
		String output = "";
		
		output += "ALabel\n";
		output += super.x + " " + super.y + " " + super.height + " " + super.width + "\n";
		output += super.caption + "\n";
		
		return output;
	}

}
