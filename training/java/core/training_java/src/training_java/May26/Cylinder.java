package training_java.May26;

public class Circle{
	double radius;
	public Circle(double radius) {
		if(radius<0) {
			this.radius=0;
		}
		else {
			this.radius=radius;
		}	
	}
	public double getRadius() {
		return radius;
		
	}
	public double getArea() {
		return Math.PI*radius*radius;
	}
}

public class Cylinder extends Circle{
	double height;
	public Cylinder(double radius,double height) {
		super(radius);
		
	}
	

}
