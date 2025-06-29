
public class Circle {
	private double radius;
	public Circle(double radius) {
		this.radius=radius<0?0:radius;
	}
	public double getRadius() {
		return radius;
	}
	public double getArea() {
		return(radius*radius*Math.PI);
	}
	
}

/**
 * 
 */
class Cylinder extends Circle{
	private double height;
	
	public Cylinder(double radius,Double height) {
		super(radius);
		this.height=height<0?0:height;
	}

	public double getHeight() {
		return height;
	}
	
	public double getVolume() {
		return height*getArea();
	}
	
}
