package draganddroid;

import java.awt.Frame;
import java.awt.Graphics;

public abstract class AndroidElement {
	int height, width; 
	Frame parent;
	
	public AndroidElement()
	{
		
	}
	
	public abstract void draw(Graphics g);
	
	
}
