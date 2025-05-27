package com;

public class Circle {
	double radius;
	final static double PI = 3.14;
	
	Circle(double radius) {
		if(radius < 0) this.radius = 0;
		else this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getArea() {
		return radius*radius*PI;
	}
}
