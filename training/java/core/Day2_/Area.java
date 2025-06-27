package com.Day2_;


public class Area {
	public static void main(String[]args) {
		System.out.println(area(10, 5));
		System.out.println(radius(5));
	}
	public static double radius(double radius) {
		if (radius<0) {
			return -1.0;
		}
		return radius * radius * Math.PI;
		
	}
	public static double area(double x, double y) {
		if ((x<0)||(y<0)) {
			return -1.0;
		}
		return x*y;
	}
}
