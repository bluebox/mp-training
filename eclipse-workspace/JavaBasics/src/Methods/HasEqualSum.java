package Methods;

import java.util.Scanner;

public class HasEqualSum {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int firstNumber = scanner.nextInt();
		int secondNumber = scanner.nextInt();
		int thirdNumber = scanner.nextInt();
		boolean result = hasEqualSum(firstNumber, secondNumber, thirdNumber);
		System.out.println(result);
		scanner.close();
	}
	public static boolean hasEqualSum(int firstNumber, int secondNumber,int thirdNumber) {
		return (firstNumber + secondNumber == thirdNumber);
	}
}
