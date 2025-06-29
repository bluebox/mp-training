
public class Rectangle {
	private double width;
	private double length;
	
	public Rectangle(double width, double length) {
		this.length=length<0?0:length;
		this.width=width<0?0:width;
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

class Cuboid extends Rectangle{
	private double height;
	
	public Cuboid(double width,double height,double length) {
		super(width,length);
		this.height=height;
	}

	public double getHeight() {
		return height;
	}
	
	public double getVolume() {
		return height*getArea();
	}
}