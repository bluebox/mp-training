package Methods;

import java.util.Scanner;

public class AreEqualThreeDecimalPlaces {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		double firstDoubleNumber = scanner.nextDouble();
		double secondDoubleNumber = scanner.nextDouble();
		boolean result = areEqualThreeDecimalPlaces(firstDoubleNumber, secondDoubleNumber);
		System.out.println(result);
		scanner.close();
	}
	public static boolean areEqualThreeDecimalPlaces(double firstDoubleNumber,double secondDoubleNumber) {
		return ((int)(firstDoubleNumber*1000) == (int)(secondDoubleNumber*1000));
	}
}
