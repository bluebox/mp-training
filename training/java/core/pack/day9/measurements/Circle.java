package day9.measurements;

public class Circle {
private double radius;
Circle(double radius){
	this.radius=radius<0 ?0:radius;
}
public double getRadius() {
	return this.radius;
}
public double getArea() {
	return this.radius*this.radius*Math.PI;
}
}
