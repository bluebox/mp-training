package calculateVolumeInheritance;

public class Rectangle {
	double width;
	double length;
	public Rectangle(double width, double length) {
		super();
		this.width = width;
		this.length = length;
	}
	
	public double getWidth() {
		return width;
	}
	public double getLength() {
		return length;
	}
	public double getArea()
	{
		return this.length * this.width;
	}
	
}
