package com.medplus;

import java.util.Scanner;

public class AreaCalculator {
	public static double area(double radius) {
		if(radius < 0) return -1.0;
		return (radius*22)/7;
	}
	public static double area(double length,double width) {
		if(length < 0 || width <0) return -1.0;
		return length*width;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("To calculate area circle enter 1 , for rectangle enter 2 :");
		int type = scanner.nextInt();
		double radius = 0,length = 0,width = 0;
		if (type == 1) {
			System.out.print("\nEnter value of radius : ");
			radius = scanner.nextInt();
		}else {
			System.out.print("\nEnter value of length : ");
			length = scanner.nextInt();
			System.out.print("\nEnter value of width : ");
			width = scanner.nextInt();
		}
		
		String areaOfShape = (type == 1) ? 
				((area(radius) == -1.0) ? "Invalid Input" : "Area of circle : "+area(radius))
				:
					((area(length,width) == -1.0) ? "Invalid Input" : "Area of circle : "+area(length,width));
		
		System.out.print("\n"+areaOfShape);
		
		scanner.close();
	}

}
