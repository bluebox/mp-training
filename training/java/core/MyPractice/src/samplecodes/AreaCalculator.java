package samplecodes;

public class AreaCalculator {
	public static void main(String[] args) {
		System.out.println(area(5.0));
		System.out.println(area(5.0,4.0));
	}
	public static double area(double radius) {
		if(radius<0) return -1.0d;
		return 3.142857*radius*radius;
	}
	public static double area(double l,double b) {
		if(l<0 || b<0) return -1.0d;
		return l*b;
	}
}

