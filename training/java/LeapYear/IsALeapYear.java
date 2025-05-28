package LeapYear;

public class IsALeapYear {

	public static void main(String[] args) {
		int year=1999;
		System.out.println(year+" is a "+(isLeapYear(year)?"Leap Year":"is not a Leap Year"));
	}
	public static boolean isLeapYear(int year)
	{
		boolean isALeap=false;
		if(year%4 == 0)
		{
			if(year%100 ==0 && year%400 == 0)
			{
				isALeap=true;
			}
			else if(year%100 != 0)
			{
				isALeap=true;
			}
		}
		return isALeap;
	}

}
