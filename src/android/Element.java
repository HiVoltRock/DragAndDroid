package android;

public class Element {

	private String type, name, caption;
	private int x, y, width, height, left, right, top, bottom; 
	
	public Element() {
		
	}

	//type of element used. e.g., button, text box, etc
	public void setType(String type) {
		this.type = type;
	}

	//type of element used. e.g., button, text box, etc
	public String getType() {
		return type;
	}

	//identified by Android "id", the name in the program
	public void setName(String name) {
		this.name = name;
	}

	//identified by Android "id", the name in the program
	public String getName() {
		return name;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	//in-app viewable lable, e.g., text on a button, text beside a checkbox.
	public void setCaption(String caption) {
		this.caption = caption;
	}

	//in-app viewable lable, e.g., text on a button, text beside a checkbox.
	public String getCaption() {
		return caption;
	}

	public void setLeft(int left) {
		this.left = left;
	}
	public int getLeft() {
		return left;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getRight() {
		return right;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getTop() {
		return top;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getBottom() {
		return bottom;
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
	
}
