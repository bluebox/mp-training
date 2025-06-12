package Methods;

import java.util.Scanner;

public class PrintFactors {
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		printFactors(number);
	}
	public static void printFactors(int number) {
		if (number < 0) {
			System.out.print("Invalid Value");
			return;
		}
		for (int i = 1;i <= number;i++) {
			if (number%i == 0) {
				System.out.print(i+" ");
			}
		}
	}
}
