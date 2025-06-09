package Datatypes;

public class Test {

	public static void main(String[] args)
    {
      
        
        long minutes = 561600;
        printyerasanddays(minutes);
    }
	public static void printyerasanddays(long minutes) {

        long minutesInDay = 24 * 60;
        long minutesInYear = 365 * minutesInDay;

        
        long years = minutes / minutesInYear;
        long remainingMinutes = minutes % minutesInYear;
        long days = remainingMinutes / minutesInDay;

        
        System.out.println(minutes + " minutes is approximately " + years + " years and " + days + " days.");

        
	}
}
