package June26;

public class MonthLength {
	public static void main (String[] args) {
		/*System.out.println(isLeapYear(-1600));
		System.out.println(isLeapYear(1600));
		System.out.println(isLeapYear(2017));
		System.out.println(isLeapYear(2000));*/
		
		System.out.println(getDaysInMonth(1, 2020));
		System.out.println(getDaysInMonth(2, 2020));
		System.out.println(getDaysInMonth(2, 2018));
		System.out.println(getDaysInMonth(-1, 2020));
		System.out.println(getDaysInMonth(1, -2020));
	}
	public static boolean isLeapYear (int year) {
		if(year < 1 || year > 9999) return false;
		if(( year % 4 == 0 && year % 100 != 0 ) || (year % 400 == 0)) return true;
		return false;
	}
	public static int getDaysInMonth(int month, int year) {
		if(month < 1 || month > 12) return -1;
		if(year < 1 || year > 9999) return -1;
		int days = 0;
		switch (month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			days += 31;
			break;
		/*case 4: case 6: case 9: case 11: 
			days+=30;
			break;	*/
		case 2:
			days += 28;
			if(isLeapYear(year)) days += 1;
			break;
		default:
			days += 30;
		}
		return days;
	}
}
