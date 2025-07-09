package day_3_june27_oops_principles;

public class Circle {
	private double radius;
	final double PI=3.1415;
	public Circle(double radius) {
		super();
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getArea() {
		return PI*radius*radius;
	}
}
