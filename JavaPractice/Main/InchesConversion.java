package Main;

import java.util.Scanner;

public class InchesConversion {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		double inches = scanner.nextDouble();
		System.out.println(inches+" inch is equal to "+(inches*2.54)+" cm");
		scanner.close();
	}
}
