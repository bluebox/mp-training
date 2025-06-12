package Methods;

import java.util.Scanner;

public class GetEvenDigitSum {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		int result = getEvenDigitSum(number);
		if (result == -1) {
			System.out.println("Invalid value");
			return;
		}
		System.out.println("Even digit sum of "+number+" is "+result);
	}

	public static int getEvenDigitSum(int number) {
		if (number < 0) return -1;
		int result = 0;
		while (number > 0) {
			int lastDigit = number%10;
			if (lastDigit%2 == 0) {
				result += lastDigit;
			}
			number /= 10;
		}
		return result;
	}
}
