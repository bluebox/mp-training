package com.medplus;

import java.util.Scanner;

public class MinuteToYearAndDayCalculator {
	public static void printYearAndDays(long minutes) {
		if (minutes < 0) System.out.print("\nInvalid Input") ;
		else {
			long days = 24*60;
			long years = minutes/(days*365);
			days = (minutes/days)%365;
			System.out.printf("\n%d minutes = %d years,%d days",minutes,years,days);
					
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter minutes : ");
		long minutes = scanner.nextLong();
		printYearAndDays(minutes);
		scanner.close();
	}

}
