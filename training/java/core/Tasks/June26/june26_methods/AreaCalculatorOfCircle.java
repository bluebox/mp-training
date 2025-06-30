package corejava.june26_methods;

import java.util.Scanner;

public class AreaCalculatorOfCircle {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter '1' if you need area of a circle else enter '2' if rectangle");
		int n=sc.nextInt();
		if(n==1) {
			System.out.println("Enter the radius of the circle");
			double radius=sc.nextDouble();
			System.out.println("area of circle with radius "+radius+" is: "+area(radius));
		}
		else if(n==2) {
			System.out.println("Enter length of the rectangle");
			double len=sc.nextDouble();
			System.out.println("Enter breath of the rectangle");
			double brt=sc.nextDouble();
			System.out.println("The area of the rectangle with length "+len+" breath "+brt+" is: "+area(len,brt));
		}
		else {
			System.out.print("Enter a valid number to get area");
		}
			
	}
	public static double area(double radius) {
		if(radius<0)
			return -1;
		return 3.14*radius*radius;
	}
	public static double area(double x, double y) {
		if(x<0 && y<0)
			return -1;
		return x*y;
	}

}
