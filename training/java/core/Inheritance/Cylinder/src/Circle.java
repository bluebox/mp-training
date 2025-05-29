
public class Circle {
	public static final double pi=3.14159265359;
	private double radius;
	public Circle(double radius) {
		this.radius=radius;
	}
	public double getRadius() {
		return radius;
	}
	public double area() {
		return pi*radius*radius;
	}
}
