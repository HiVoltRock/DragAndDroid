package element;

import java.awt.Graphics;
import java.io.PrintWriter;

import editorView.OurCanvas;
import global.EventType;

/**
 * Represents an android element drawn on 
 * editor canvas
 * 
 * @author Anthony
 *
 */
public abstract class AndroidElement{
	int x, y;
	int width;
	int height; 
	String caption;	
	String name;
	
	OurCanvas parent;
	public EventType event;
	
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
	public abstract void setLeft(String left);
	public abstract String getLeft();
	public abstract void setRight(String right);
	public abstract String getRight();
	public abstract void setAbove(String above);
	public abstract String getAbove();
	public abstract void setBelow(String below);
	public abstract String getBelow();
	public abstract String getName();
	public abstract int getHeight();
	public abstract int getWidth();
	public abstract void setName(String name);
	public abstract void setHeight(int height);
	public abstract void setWidth(int width);
	public abstract void alignParentRight();
	public abstract void removeParentRight();
	public abstract String getParentRight();
	
	public abstract String getType();
	public abstract void printAndroidXml(PrintWriter pw);

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}	
}
