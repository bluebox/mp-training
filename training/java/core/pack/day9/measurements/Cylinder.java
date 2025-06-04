package day9.measurements;

public class Cylinder extends Circle {
private double height;
Cylinder(double radius,double height){
	super(radius);
	this.height=height<0?0:height;
}
public double getHeight() {
	return this.height;
}
public double getVolume() {
	return getArea()*getHeight();
}
}
