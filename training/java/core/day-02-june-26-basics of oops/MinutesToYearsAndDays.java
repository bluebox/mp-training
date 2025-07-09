package day2;

public class MinutesToYearsAndDays {

	public static void main(String[] args) {
		printYearsAndDays(525600);
		printYearsAndDays(1051200);
		printYearsAndDays(561600);
	}
	public static void printYearsAndDays(int minutes) {
		int minutesInDay=24*60;
		int days=minutes/minutesInDay;
		int years=days/365;
		days=days%365;
		System.out.println(minutes+" min = "+years+" y and "+days+" d");
	}
}
