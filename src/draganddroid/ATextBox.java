package draganddroid;

import global.Constants;

import java.awt.Color;
import java.awt.Graphics;

public class ATextBox extends AndroidElement {

	public ATextBox(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.height = Constants.ATextBoxWidth;
		this.width = Constants.ATextBoxHeight;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(217,163,151));
		g.fillRect(x, y, Constants.ATextBoxWidth, Constants.ATextBoxHeight);
		g.setColor(Color.BLACK);
		g.drawString(name, x+10, y+20);
	}

	@Override
	public String outputElementXML() {
		String output = "";
		output += "\t<Element type=\"ATextBox\" ";
		output += "name=\"" + this.name + "\" ";
		output += "x=\"" + this.x + "\" ";
		output += "y=\"" + this.y + "\" ";
		output += "height=\"" + this.height + "\" ";
		output += "width=\"" + this.width + "\" ";
		output += "caption=\"" + this.caption + "\">\n";
		output += "\t</Element>\n";
		return output;
	}

	@Override
	public boolean isInside(int x, int y) {
		for ( int i = this.x; i < this.x+width; i++ ) {
			for ( int j = this.y; j < this.y + height; j++ ) {
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
}
