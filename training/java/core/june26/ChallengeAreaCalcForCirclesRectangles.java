package com.tulasidhar.june26;

public class ChallengeAreaCalcForCirclesRectangles {
	public static void main(String[] args) {
		System.out.println(area(5.0));
		System.out.println(area(-1));
		System.out.println(area(5.0,4.0));
		System.out.println(area(-1.0,4.0));
	}
	
	public static double area(double radius) {
		if(radius < 0) {
			return -1;
		}
				
		return (3.14d * radius *radius);
	}
	
	public static double area(double x, double y) {
		if(x<0 || y<0) {
			return -1;
		}
		
		return x*y;
	}
}
