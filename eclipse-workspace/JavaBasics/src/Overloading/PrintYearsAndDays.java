package Overloading;

import java.util.Scanner;

public class PrintYearsAndDays {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		long minutes = scanner.nextLong();
		printYearsAndDays(minutes);
		scanner.close();
	}
	public static void printYearsAndDays(long minutes) {
		long years = minutes / (365 * 24 * 60);
		long remainingDays = (minutes / (24 * 60)) - (years * 365);
		System.out.println(minutes+" min = "+years+" y and "+remainingDays+" d");
	}
}
