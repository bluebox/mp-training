package Methods;

import java.util.Scanner;

public class PalindromeNumber {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		boolean result = isPalindrome(number);
		System.out.println(result);
	}

	public static boolean isPalindrome(int number) {
		int result = 0, actualNumber = number;
		while (number != 0) {
			result = 10*result + number%10;
			number /= 10;
		}
		return (result == actualNumber);
	}
}
