package CylinderClass;

public class Circle {
	public double radius;
	public Circle(double radius) {
		if(radius<0) this.radius=radius;
		else this.radius=radius;
	}
	public double getRadius() {
		return radius;
	}
	public double getArea() {
		return radius*radius*3.14d;
	}
}
