package challenge2;

public class Rectangle {
	
	private double width;
	private double length;
	public Rectangle(double width, double length) {
		super();
		this.width = width;
		if(width<0)
			width=0;
		this.length = length;
		if(length<0)
			length=0;
	}
	public double getWidth() {
		return width;
	}
	public double getLength() {
		return length;
	}
	public double getArea() {
		return length*width;
	}
	
	

}
