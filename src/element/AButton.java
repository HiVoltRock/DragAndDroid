package element;

import global.Constants;
import global.EventType;

import java.awt.Color;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.util.Vector;

public class AButton extends AndroidElement {
	
	public String left = "";
	public String right = "";
	public String above = "";
	public String below = "";
	
	public AButton(){};
	
	public AButton(String name, int x, int y){
		this.setName(name);
		this.x = x;
		this.y = y;
		this.setHeight(Constants.AButtonWidth);
		this.setWidth(Constants.AButtonHeight);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, Constants.AButtonWidth, Constants.AButtonHeight);
		g.setColor(Color.BLACK);
		g.drawString(getName(), x+10, y+20);
		
	}

	@Override
	public String outputElementXML() {
		String output = "";
		output += "\t<Element type=\"AButton\" ";
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
		pw.println("\t<Button");
		pw.println("\t\tandroid:id=\"@+id/" + getName() + "\"");
		pw.println("\t\tandroid:layout_width=\"wrap_content\"");
		pw.println("\t\tandroid:layout_height=\"wrap_content\"");
		pw.println("\t\tandroid:text=\"" + getName() + "\"");
		
		if(!left.equals(""))
		{
			pw.println("\t\tandroid:layout_toLeftOf=\"@id/" + left + "\"");
		}
		
		if(!right.equals(""))
		{
			pw.println("\t\tandroid:layout_toRightOf=\"@id/" + right + "\"");
		}
		
		if(!below.equals(""))
		{
			pw.println("\t\tandroid:layout_below=\"@id/" + below + "\"");
		}
		
		if(!above.equals(""))
		{
			pw.println("android:layout_above=\"@id/" + above + "\"");
		}
		pw.println("\t/>");
	}
	
	public String getType() {
		return "AButton";
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
	public void addEvent(EventType e) {
		this.elementEventList.add(e);
	}

	@Override
	public Vector<EventType> getEvents() {
		return this.elementEventList;
	}
}
