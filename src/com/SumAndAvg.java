package com;

import java.util.Scanner;

public class SumAndAvg {
	public static void main(String[] args) {
		InputThenPrintSumAndAverage();
	}
	public static void InputThenPrintSumAndAverage() {
		Scanner scanner = new Scanner(System.in);
		
		long sum = 0, avg = 0;
		int cnt = 0;
		
		do {
			System.out.println("Enter a number:");
			String input = scanner.nextLine();
			
			try {
				int number = Integer.parseInt(input);
				cnt++;
				
				sum += number;
			}
			catch (Exception e) {
				break;
			}
		} while (true);
		
		
		System.out.println("Loop terminated abruptly since you entered a character instead of a number !");
		
		if(cnt > 0) {
			avg = sum/cnt;
			System.out.println("Sum = " + sum + " Average = " + avg);
		}
		else {
			System.out.println("Sum = " + sum + " Average = " + avg);
		}
	}
}
