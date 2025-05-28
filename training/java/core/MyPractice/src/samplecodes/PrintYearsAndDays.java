package samplecodes;

public class PrintYearsAndDays {

	public static void main(String[] args) {
		printYearsAndDays(561600);
	}
	public static void printYearsAndDays(long minutes) {
		long hours=minutes/60;
		long days=hours/24;
		long years=days/365;
		days=days%365;
		System.out.println(years+"y and "+days+" d");
	}

}
