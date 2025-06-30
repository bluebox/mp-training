
public class TimeConverter {
		public static void printYearsAndDays(long minutes) {
			if(minutes<0) {
				System.out.println("invalid value");
			}
			long minutesDay=60*24;
			long minutesInYear=minutesDay*365;
			long years=minutes/minutesInYear;
			long remainingMinutes=minutes%minutesInYear;
			long days=remainingMinutes/minutesDay;
			
			System.out.println(minutes+"minutes = "+years+"y and "+days+" d");;
		}
}
