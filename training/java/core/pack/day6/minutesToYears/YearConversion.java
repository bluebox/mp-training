package day6.minutesToYears;

public class YearConversion {
	public static void minutesToYears(long t) {
		long minutesInYear=365*24*60;
		long minutesInDay=24*60;
		
		long years=t/minutesInYear;
		long remainingMinutes=t%minutesInYear;
		long days=remainingMinutes/minutesInDay;
		
		System.out.println(t+" min = "+years+"  y and "+days +" d");
	}
}
