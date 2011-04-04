package draganddroid;

import java.awt.Graphics;

public class ATextBox extends AndroidElement {

	public ATextBox(String name) {
		super.name = name;
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String outputElement() {
		String output = "";
		
		output += "ATextBox\n";
		output += super.name + "\n";
		output += super.x + " " + super.y + " " + super.height + " " + super.width + "\n";
		output += super.caption + "\n";		
		
		return output;
	}

}
