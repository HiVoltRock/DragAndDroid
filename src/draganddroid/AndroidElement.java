package draganddroid;

import java.awt.Graphics;

/**
 * Represents an android element drawn on 
 * editor canvas
 * 
 * @author Anthony
 *
 */
public abstract class AndroidElement{
	int height, width, x, y; 
	String caption;	
	String name;
	
	OurCanvas parent;
	
	public AndroidElement()
	{
		
	}
	
	/**
	 * draws particular android element on canvas
	 * @param g
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * returns an xml representation of this 
	 * android element
	 */
	public abstract String outputElementXML();
	
	/**
	 * determines whether x and y coordinate are inside 
	 * of this element
	 */
	public abstract boolean isInside(int x, int y);

    public abstract int getX();
	public abstract int getY();
	public abstract void setX(int x);
	public abstract void setY(int y);
	
}
