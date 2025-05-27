package Day_3_26May;

import java.util.*;

public class EnhancedPalindrome {
	public static boolean isPalindromeNumber(int input) {
		if (input < 0)
			input = input * -1;
		int temp = input;
		int sum = 0;
		while (temp > 0) {
			int remainder = temp % 10;// gives last digit
			sum = sum * 10 + remainder;
			temp /= 10;

		}
		if (sum == input)
			return true;
		else
			return false;

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		boolean isPalindrome = isPalindromeNumber(input);
		if (isPalindrome)
			System.out.println(input + " is Palindrome.");
		else
			System.out.println(input + " is not a Palindrome.");

		sc.close();
	}

}
