package day_2_june26_basics_of_oops;

public class DaysInMonth {
	public static void main(String args[]) {
		System.out.println(getDaysInMonth(1, 2020));  
		System.out.println(getDaysInMonth(2, 2020));  
		System.out.println(getDaysInMonth(2, 2016));  
		System.out.println(getDaysInMonth(-1, 2020)); 
		System.out.println(getDaysInMonth(1, -2020)); 
	}

	public static int getDaysInMonth(int month, int year) {
		if (month < 1 || month > 12 || year < 0) return -1;

		int[] days = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		if (LeapYear.isLeapYear(year) && month == 2)
			return 29;

		return days[month - 1];
	}
}
