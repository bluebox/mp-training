package Day2_26_06;

public class LeapYear {
	public static void main(String args[]) {
		System.out.println(getDaysinMonth(2,1604));
		System.out.println(getDaysinMonth(2,1600));
		System.out.println(getDaysinMonth(2,1500));
		System.out.println(getDaysinMonth(2,25));
		System.out.println(getDaysinMonth(2,-1));

	}
	public static boolean isLeap(int year) {
		if(year<=0 || year>9999) {
			return false;
		}
		if(year %400 ==0) {
			return true;
		}else if(year %100 ==0) {
			return false;
		}else if(year % 4 ==0) {
			return true;
		}
		return false;
		}
	public static int getDaysinMonth(int month,int year) {
		if(year<=0) {
			return -1;
		}
		switch(month) {
		case 1:
			return 31;
		case 2:
			if(isLeap(year)) {
				return 29;
			}
			return 28;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		default:
			return -1;
		}
	}
}
