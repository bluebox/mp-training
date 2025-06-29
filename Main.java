package Day3;

public class Main {
public static void main(String [] args) {
	Circle circle=new Circle(3.75);
	System.out.println(circle.getArea()+" "+circle.getRadius());
	Cylinder cylinder=new Cylinder(5.55,7.25);
	System.out.println(cylinder.getArea()+" "+cylinder.getRadius()+" "
	+cylinder.getHeight()+" "+cylinder.getVolume());
}
}

class Circle{
	private double radius;
	
	public Circle(double radius) {
		if(radius>=0)this.radius=radius;
	}

	public double getRadius() {
		return radius;
	}
	
	public double getArea() {
		return (Math.PI)*radius*radius;
	}
	
}

class Cylinder extends Circle{

	private double height;
	
	public Cylinder(double radius,double height) {
		super(radius);
		if(height>=0)this.height=height;
	}

	public double getHeight() {
		return height;
	}
	
	public double getVolume() {
		return this.getHeight()*this.getArea();
	}
	
}


