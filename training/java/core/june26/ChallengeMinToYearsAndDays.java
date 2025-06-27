package com.tulasidhar.june26;

public class ChallengeMinToYearsAndDays {
	public static void main(String[] args) {
		printYearsAndDays(561600);
	}
	
	public static void printYearsAndDays(long minutes) {
		if(minutes == 0) {
			System.out.println("Invalid value");
		}
		else {
						
			long hours = minutes/60;
			
			long days = hours /24;
			long years = days/365;
			days = days % 365;
			
			System.out.println(minutes+" mins = "+years+" years and "+ days+" days");
		}
	}
}
