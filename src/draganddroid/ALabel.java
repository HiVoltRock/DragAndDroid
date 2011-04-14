package draganddroid;

import global.Constants;

import java.awt.Color;
import java.awt.Graphics;
import java.io.PrintWriter;

public class ALabel extends AndroidElement {

	public ALabel(){};
	public ALabel(String name, int x, int y) {
		this.setName(name);
		this.x = x;
		this.y = y;
		this.setHeight(Constants.ALabelWidth);
		this.setWidth(Constants.ALabelHeight);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(157,211,207));
		g.fillRect(x, y, Constants.ALabelWidth, Constants.ALabelHeight);
		g.setColor(Color.BLACK);
		g.drawString(getName(), x+10, y+20);	
	}

	@Override
	public String outputElementXML() {
		String output = "";
		output += "\t<Element type=\"ALabel\" ";
		output += "name=\"" + this.getName() + "\" ";
		output += "x=\"" + this.x + "\" ";
		output += "y=\"" + this.y + "\" ";
		output += "height=\"" + this.getHeight() + "\" ";
		output += "width=\"" + this.getWidth() + "\" ";
		output += "caption=\"" + this.caption + "\">\n";
		output += "\t</Element>\n";
		return output;
	}

	@Override
	public boolean isInside(int x, int y) {
		for ( int i = this.x; i < this.x+getWidth(); i++ ) {
			for ( int j = this.y; j < this.y + getHeight(); j++ ) {
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

	@Override
	public String getAbove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBelow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAbove(String above) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBelow(String below) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLeft(String left) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRight(String right) {
		// TODO Auto-generated method stub
		
	}
	public String getType() {
		return "ALabel";
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
	/**
	 * Serious problem: It seems like we have a problem adding Strings in general (comment added here because this happened to be where
	 * I was when I noticed it). I entered the string "For the love of God, work" for a label and I got an array index out of bounds exception.
	 * TODO: fix that crap
	 */
	public void printAndroidXml(PrintWriter pw) 
	{
		pw.println("\t<TextView");
		pw.println("\t\tandroid:id=\"@+id/" + getName() + "\"");
		pw.println("\t\tandroid:layout_width=\"fill_parent\"");
		pw.println("\t\tandroid:layout_height=\"wrap_content\"");
		pw.println("\t\tandroid:text=\"" + getName() + "\"");
		
		pw.println("\t/>");
		
	}

}
