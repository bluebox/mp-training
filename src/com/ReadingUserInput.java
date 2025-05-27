package com;

import java.util.Scanner;

public class ReadingUserInput {
	
	public static void main(String[] args) {
		int cnt = 0, totalSum = 0;
		Scanner scanner = new Scanner(System.in);
		
		do {
			System.out.println("Enter number #" + (cnt+1));
			String input = scanner.nextLine();
			try {
				int number = Integer.parseInt(input);
				totalSum += number;
				cnt++;
			}
			catch (Exception e) {
				System.out.println("Please enter a Valid Number!");
			}
		} while (cnt!=5);
		
		System.out.println("The sum of the " + cnt + " input numbers is " + totalSum);
		
		scanner.close();
	}
	
}
