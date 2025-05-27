package medplus.ints;

import java.util.Scanner;

public class PrintFactors {
	public static void printFactors(int value) {
		System.out.print("\nFactors of "+value+" : ");
		for (int i =1; i <= value;i++) {
			if (value%i==0)System.out.print(i+" ");
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number to know factors : ");
		int value = scanner.nextInt();
		printFactors(value);
		scanner.close();
	}
}
