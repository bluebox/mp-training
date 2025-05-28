package Basics;

import java.util.Scanner;

public class SumAndAverage {

	public static void PrintSumAndAverage() {
		Scanner sc = new Scanner(System.in);

		int sum = 0, count = 0;

		while (true) {
			String input = sc.nextLine();
			if (input.matches("\\d+")) {
				System.out.println("");
				int number=Integer.parseInt(input);
				sum+=number;
				count++;
			} else {
				System.out.println("The input does not contain only digits.");
				break;
			}
		}
		System.out.println("The sum of digits entered: "+sum);
		System.out.println("The average of digits enterred: "+(int)sum/count);

	}

	public static void main(String[] args) {
		System.out.println("This program enables user to enter numbers until user press any character.\n"
				+ "Then returns sum and average of numbers entered");

		PrintSumAndAverage();
	}

}
