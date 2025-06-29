public class CylinderCircleCalc {
    
    static class Circle {
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
static class Cylinder extends Circle{	
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

public static void main(String[] args) {
    Cylinder cy = new Cylinder(5.55,7.25);
		System.out.println(cy.getArea());
		System.out.println(cy.getVolume());
		System.out.println(cy.getHeight());
		System.out.println(cy.getRadius());
}
}
