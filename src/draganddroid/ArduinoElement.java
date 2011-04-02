package draganddroid;

import java.awt.Frame;
import java.awt.Graphics;

public abstract class ArduinoElement {
	int height, width; 
	Frame parent;
	
	public ArduinoElement()
	{
		
	}
	
	public abstract void draw(Graphics g);
	
	
    }
