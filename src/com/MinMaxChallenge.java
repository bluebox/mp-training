package com;

import java.util.Scanner;

public class MinMaxChallenge {
	public static void main(String[] args) {
		int minNum = Integer.MAX_VALUE, maxNum = Integer.MIN_VALUE;
		Scanner scanner = new Scanner(System.in);
		int cnt = 0;
		
		do {
			System.out.println("Enter a number:");
			String input = scanner.nextLine();
			
			try {
				int number = Integer.parseInt(input);
				cnt++;
				
				minNum = Math.min(minNum, number);
				maxNum = Math.max(maxNum, number);
			}
			catch (Exception e) {
				break;
			}
		} while (true);
		
		System.out.println("Loop terminated abruptly since you entered a character instead of a number !");
		
		if(cnt > 0) {
			System.out.println("Min number = " + minNum + "\nMax number = " + maxNum);
		}
		else {
			System.out.println("You have not entered any valid number till now !");
		}
	}
}
