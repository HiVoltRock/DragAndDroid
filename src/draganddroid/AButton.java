package draganddroid;

import java.awt.Graphics;

public class AButton extends AndroidElement {
	
	public AButton(int x, int y){
		super.x = x;
		super.y = y;
		super.height = 20;
		super.width = 50;
	}
	
	@Override
	public void draw(Graphics g) {
		
	}

	@Override
	public String outputElement() {
		String output = "";
		
		output += "AButton\n";
		output += super.x + " " + super.y + " " + super.height + " " + super.width + "\n";
		output += super.caption + "\n";
		
		return output;
	}
		
}
