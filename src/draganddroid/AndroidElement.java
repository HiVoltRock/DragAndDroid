package draganddroid;

import java.awt.Graphics;

public abstract class AndroidElement {
	int height, width, x, y; 
	String caption;	
	String name;
	
	OurCanvas parent;
	
	public AndroidElement()
	{
		
	}
	
	public abstract void draw(Graphics g);
	
	public abstract String outputElement();
	
}
