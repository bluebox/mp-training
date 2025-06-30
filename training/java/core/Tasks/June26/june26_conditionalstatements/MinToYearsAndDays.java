package corejava.june26_conditionalstatements;

import java.util.Scanner;

public class MinToYearsAndDays {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the minuts");
		long min=sc.nextLong();
		printYearsAndDays(min);
		sc.close();
	}
	public static void printYearsAndDays(long minutes) {
		if(minutes<0) {
			System.out.println("Invalid input");
		}
		else {
			long originalmin=minutes;
			long hours= minutes/60;
			long days=hours/24;
			long years=days/365;
			minutes%=60;
			days%=24;
			System.out.println(originalmin+" = "+years+"years and "+days+" days");
		}
	}
}
