package Inheritance;

class Circle {

	private double radius;

	public Circle(double radius) {

		this.radius = radius;

	}

	public double getRadius() {
		return radius;
	}

	public double getArea() {
		return radius * radius * 3.14;
	}
}

class Cylinder extends Circle {
	private double height;

	public Cylinder(double radius, double height) {
		super(radius);
		if (height < 0) {
			this.height = 0;
		} else {
			this.height = height;
		}
	}

	public double getHeight() {
		return height;
	}

	public double getVolume() {
		return getArea() * height;
	}

}

public class ShapesChallenge {
	public static void main(String[] args) {
		Circle circle = new Circle(3.44);
		System.out.println("Radius of Circle" + circle.getRadius());
		System.out.println("Area of circle " + circle.getArea());
		
		Cylinder cylinder=new Cylinder(5.5,6.5);
		System.out.println("Radius of cylinder" + cylinder.getRadius());
		System.out.println("Height of cylinder " + cylinder.getHeight());
		System.out.println("Volume of cylinder " + cylinder.getVolume());
	}

}
