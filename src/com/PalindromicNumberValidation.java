package com;

import java.util.Scanner;

public class PalindromicNumberValidation {
	
	private static int reversedNumber(int number) {
		int revNum = 0;
		while(number > 0) {
			int digit = number%10;
			revNum = revNum*10 + digit;
			number /= 10;
		}
		return revNum;
	}
	
	private static boolean isPalindrome(int number) {
		System.out.println(reversedNumber(number));
		if(reversedNumber(number) == number) return true;
		return false;
	}
	
	public static void main(String []args) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		System.out.println(isPalindrome(number));
		scanner.close();
	}
}
