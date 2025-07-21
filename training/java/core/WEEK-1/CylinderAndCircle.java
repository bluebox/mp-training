class Circle {
	double radius;
	
	public Circle(double radius) {
		this.radius = radius < 0 ? 0 : radius;
	}
	
	public double getRadius() {
		return radius;
	}
	public double getArea() {
		return Math.pow(radius,2) * Math.PI;
	}
}

class Cylinder extends Circle{	
	double height;
	
	public Cylinder(double radius, double height) {
		super(radius);
		this.height = height < 0 ? 0 : height;
	}
	
	public double getHeight() {
		return this.height;
	}
	public double getVolume() {
		return Math.PI* Math.pow(super.radius,2) * this.height;
	}
}

public class CylinderAndCircle{
	public static void main(String[] args) {
		Cylinder cylinder = new Cylinder(5, 10);
		System.out.println("Cylinder Area: " + cylinder.getArea());
		System.out.println("Cylinder Volume: " + cylinder.getVolume());
		System.out.println("Cylinder Radius: " + cylinder.getRadius());
		System.out.println("Cylinder Height: " + cylinder.getHeight());
	}
}