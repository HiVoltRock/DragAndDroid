package draganddroid;

import java.awt.Graphics;

public abstract class AndroidElement{
	int height, width, x, y; 
	String caption;	
	String name;
	
	OurCanvas parent;
	
	public AndroidElement()
	{
		
	}
	
	public abstract void draw(Graphics g);
	
	public abstract String outputElementXML();
	
	public abstract boolean isInside(int x, int y);

                 public abstract int getX();
	public abstract int getY();
	public abstract void setX(int x);
	public abstract void setY(int y);
	
}
