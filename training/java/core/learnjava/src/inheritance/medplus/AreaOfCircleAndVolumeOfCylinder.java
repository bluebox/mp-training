package inheritance.medplus;

public class AreaOfCircleAndVolumeOfCylinder {
	
	public static void main(String[] args) {
		Cylinder cylinder = new Cylinder(5,6);
		System.out.println("Height of the cylinder : "+cylinder.getHeight());
		System.out.println("Radius of the Cylinder : "+cylinder.getRadius());
		System.out.println("Volume of the Cylinder : "+cylinder.getVolume());
		Circle circle = new Circle(0);
		System.out.println("Radius of the circle : "+circle.getRadius());
		System.out.println("Area of the Circle : "+circle.getArea());
	}

}
class Circle{
	private double radius;
	
	public Circle(double radius) {
		if (radius <0) this.radius =0;
		else this.radius = radius;
	}
	
	public double getRadius() {
		return this.radius;
	}
	public double getArea() {
		return radius*radius*Math.PI;
	}
}

class Cylinder extends Circle{
	
	private double height;
	
	
	public Cylinder(double radius , double height) {
		super(radius);
		if (height < 0 ) this.height = 0;
		else this.height = height;
		
	}
	
	public double getHeight() {
		return this.height;
	}
	public double getVolume() {
		return height*this.getArea();
	}
}