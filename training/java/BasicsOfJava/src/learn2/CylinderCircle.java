package learn2;

public class CylinderCircle {
	public static void main(String[] args) {
		
		Circle smallCircle = new Circle(7.0);
		
		
		System.out.println(smallCircle.getArea());
		System.out.println(smallCircle.getRadius());
		
		
		Cylinder cylinder = new Cylinder(7.0,7.0);
		
		System.out.println("area of the base of cylinder is "+cylinder.getArea());
		System.out.println("radius of the base of cylinder is "+cylinder.getRadius());
		System.out.println("height of the cylinder is "+cylinder.getHeight());
		System.out.println("volume of cylinder is "+cylinder.getVolume());
		System.out.println(cylinder.toString());
		System.out.println(cylinder.getClass().getSimpleName());
	}
}

class Circle{
	
	private double radius;
	public Circle(double radius) {
		if(radius < 0) {
			this.radius = 0;
		}
		else {
			this.radius = radius;
		}
	}
	public double getRadius() {
		
		return radius;
	}
	public double getArea() {
		double area = Math.PI * radius * radius;
		return area;
	}
}

class Cylinder extends Circle{
	
	private double height;
	
	public Cylinder(double height,double radius) {
		super(radius);
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
	public double getVolume() {
		return this.getArea() * height;
	}
	
}