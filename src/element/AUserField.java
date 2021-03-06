package element;

import global.Constants;

import java.awt.Color;
import java.awt.Graphics;
import java.io.PrintWriter;

/**
 * a type of android element that represents
 * an area where the user can write code to incorporate any
 * other elements or effects beyond the scope of this sopftware
 * 
 * @author Anthony
 *
 */
public class AUserField extends AndroidElement {
	
	public AUserField(){};
	
	public String left = "";
	public String right = "";
	public String above = "";
	public String below = "";
	public String alignParentRight = "";
	
	public AUserField(String name, int x, int y){
		this.setName(name);
		this.x = x;
		this.y = y;
		this.setHeight(Constants.AUserFieldWidth);
		this.setWidth(Constants.AUserFieldHeight);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawString(getName(), x+10, y+20);
	}

	@Override
	public String outputElementXML() {
		String output = "";
		output += "\t<Element type=\"AUserField\" ";
		output += "name=\"" + this.getName() + "\" ";
		output += "x=\"" + this.x + "\" ";
		output += "y=\"" + this.y + "\" ";
		output += "height=\"" + this.getHeight() + "\" ";
		output += "width=\"" + this.getWidth() + "\" ";
		output += "caption=\"" + this.getCaption() + "\">\n";
		output += "\t</Element>\n";
		return output;
	}

	@Override
	public boolean isInside(int x, int y) {
		for ( int i = this.x; i < this.x+this.getWidth(); i++ ) {
			for ( int j = this.y; j < this.y + this.getHeight(); j++ ) {
				if ( x == i && y == j ) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;	
	}
	
	public void setLeft(String left)
	{
		this.left = left;
	}
	
	public String getLeft()
	{
		return left;
	}
	
	public void setRight(String right)
	{
		this.right = right;
	}
	
	public String getRight()
	{
		return right;
	}
	
	public void setAbove(String above)
	{
		this.above = above;
	}
	
	public String getAbove()
	{
		return above;
	}
	
	public void setBelow(String below)
	{
		this.below = below;
	}
	
	public String getBelow()
	{
		return below;
	}
	
	public void printAndroidXml(PrintWriter pw)
	{
		//TODO cory
	}
	
	public String getType() {
		return "AUserField";
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public void alignParentRight() 
	{
		this.alignParentRight = "true";
		
	}
	
	@Override
	public void removeParentRight()
	{
		this.alignParentRight = "";
	}

	@Override
	public String getParentRight() 
	{
		return this.alignParentRight;
	}

}
