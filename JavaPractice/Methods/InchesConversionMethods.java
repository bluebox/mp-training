package Methods;

import java.util.Scanner;

public class InchesConversionMethods {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		double inches = scanner.nextDouble();
		inchesToCentimeters(inches);
		scanner.close();
	}
	public static void inchesToCentimeters(double inches) {
		 System.out.println(inches+" inches is equal to "+(inches * 2.54)+" cm");
	}
}
