package com.tulasidhar.june27.CylinderWithCircle;

public class Circle {
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
