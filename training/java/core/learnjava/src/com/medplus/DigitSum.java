package com.medplus;

import java.util.Scanner;

public class DigitSum {
	public static int sumDigits(int number) {
		if (number < 0) return -1;
		else{
			int digitsum = 0;
			while (number !=0) {
				digitsum += (number%10);
				number = number /10;
			}
			return digitsum;
		}
		
	}
		
		public static void main(String[] args) {
			System.out.print("Enter a number : ");
			Scanner scanner = new Scanner(System.in);
			int number = scanner.nextInt();
			if (sumDigits(number) == -1) System.out.print("\nInvalid Input");
			else System.out.print("\nSum of digits : "+sumDigits(number));
			scanner.close();
		}

}
