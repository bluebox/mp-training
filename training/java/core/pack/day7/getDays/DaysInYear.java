package day7.getDays;
import day6.leapYear.*;

public class DaysInYear {
	public static int getMonthDays(int month,int year) {
		LeapOrNot l=new LeapOrNot();
		boolean leap=l.isLeapYear(year);
		int res;
		switch(month) {
		case 1:
			res=31;
			break;
		case 2:
			if(leap)
			res=29;
			else 
				res=28;
			break;
		case 3:
			res=31;
			break;
		case 4:
			res=30;
			break;
		case 5:
			res=31;
			break;
		case 6:
			res=30;
			break;
		case 7:
			res=31;
			break;
		case 8:
			res=30;
			break;
		case 9:
			res=31;
			break;
		case 10:
			res=31;
			break;
		case 11:
			res=30;
			break;
		case 12:
			res=31;
			break;
		default:
			res=-1;
			
		}
		return res;
	}
}
