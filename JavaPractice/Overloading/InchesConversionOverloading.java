package Overloading;

import java.util.Scanner;

public class InchesConversionOverloading {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		double inches = scanner.nextDouble();
		inchesToCentimeters(inches);
		
		double feet = scanner.nextDouble();
		inches = scanner.nextDouble();
		inchesToCentimeters(feet, inches);
		scanner.close();
	}
	public static void inchesToCentimeters(double feet,double inches) {
		double feetConvo = feet*12;
		inchesToCentimeters(feetConvo+inches);
	}
	public static void inchesToCentimeters(double inches) {
		 System.out.println(inches+" inches is equal to "+(inches * 2.54)+" cm");
	}
}
