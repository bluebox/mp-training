package CylinderClass;

public class Cylinder extends Circle{
	public double height;
	
	public Cylinder(double radius,double height) {
		super(radius);
		if(height <0) this.height=0;
		else this.height=height;
	}
	public double getHeight() {
		return height;
	}
	public double getVolume() {
		double area=getArea();
		return area*height;
	}
}
