package Overloading;

import java.util.Scanner;

public class AreaCalculator {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		
		double radius = scanner.nextDouble();
		if (radius < 0.0) {
			System.out.println("Invalid value");
			return;
		}
		double areaCircle = area(radius);
		System.out.println("Area of circle for given radius is: "+areaCircle);
		
		double x = scanner.nextDouble();
		double y = scanner.nextDouble();
		if (x < 0.0 || y < 0.0) {
			System.out.println("Invalid value");
			return;
		}
		double areaRectangle = area(x,y);
		System.out.println("Area of rectangle for given dimension is: "+areaRectangle);
		scanner.close();
		
	}
	public static double area(double radius) {
		return 3.14159265359 * radius * radius;
	}
	public static double area(double x,double y) {
		return x * y;
	}
}
