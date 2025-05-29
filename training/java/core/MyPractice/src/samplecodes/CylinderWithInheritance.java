package samplecodes;

public class CylinderWithInheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c=new Circle(3.75);
		System.out.println("Circle Radius is "+c.getRadius());
		System.out.println("Circle Area is "+c.getArea());
		
		Cylinder cylinder =new Cylinder(4,5);
		System.out.println("Cylinder Height is "+cylinder.getHeight());
		System.out.println("Cylinder Area is "+cylinder.getVolume());
	}
}
class Circle{
	private double radius;
	Circle(double radius){
		if(radius<0)this.radius=0;
		else this.radius=radius;
	}
	public double getRadius() {
		return this.radius;
	}
	public double getArea() {
		double PI=Math.PI;
		return (radius*radius*PI);
	}
}
class Cylinder extends Circle{
	private double height;
	Cylinder(double radius,double height) {
		super(radius);
		if(height<0) this.height=0;
		else this.height=height;
		// TODO Auto-generated constructor stub
	}
	public double getHeight() {
		return height;
	}
	public double getVolume() {
		return height*getArea();
	}
	
}
