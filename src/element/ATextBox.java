package element;

import global.Constants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.PrintWriter;

import javax.swing.ImageIcon;

/**
 * a type of android element that represents a text field
 * 
 * @author Anthony
 *
 */
public class ATextBox extends AndroidElement {

	public String left = "";
	public String right = "";
	public String above = "";
	public String below = "";
	public String alignParentRight = "";
	
	public ATextBox(){};
	
	public ATextBox(String name, int x, int y) {
		this.setName(name);
		this.x = x;
		this.y = y;
		this.setHeight(Constants.ATextBoxWidth);
		this.setWidth(Constants.ATextBoxHeight);
	}
	
	@Override
	public void draw(Graphics g) {
		//g.setColor(new Color(217,163,151));
		//g.fillRect(x, y, Constants.ATextBoxWidth, Constants.ATextBoxHeight);
		Image img = new ImageIcon(this.getClass().getResource("/icons/textBox.png")).getImage();
		g.drawImage(img, x, y, null);
		g.setColor(Color.BLACK);
		g.drawString(getName(), x+10, y+20);
	}

	@Override
	public String outputElementXML() {
		String output = "";
		output += "\t<Element type=\"ATextBox\" ";
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
	public String getType() {
		return "ATextBox";
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
	public void printAndroidXml(PrintWriter pw) 
	{
		pw.println("\t<EditText");
		pw.println("\t\tandroid:id=\"@+id/" + getName() + "\"");
		pw.println("\t\tandroid:layout_width=\"fill_parent\"");
		pw.println("\t\tandroid:layout_height=\"wrap_content\"");
		pw.println("\t\tandroid:background=\"@android:drawable/editbox_background\"");
		pw.println("\t\tandroid:text=\"" + "\"");
		
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
		
		if(!alignParentRight.equals(""))
		{
			pw.println("\t\tandroid:layout_alignParentRight=\"true\"");
		}
		
		pw.println("\t/>");
		
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
