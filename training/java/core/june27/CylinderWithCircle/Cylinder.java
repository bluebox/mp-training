package com.tulasidhar.june27.CylinderWithCircle;

public class Cylinder extends Circle{	
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
