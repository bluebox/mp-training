
public class Challenge8 {
	public static boolean isLeapYear(int year)
	{
		if((year%4==0 && year%100!=0)|| (year%400==0))
			return true;
		else
			return false;
	}
	
	public static int daysInMonth(int month,int year)
	{
		if((month<1 && month>12)|| (year<1 && year>9999))
			return -1;
		else
		{
			if(month==1 ||month==3|| month==5|| month==7 || month==8 ||month==10|| month==12)
				return 31;
			else if(month==2 && isLeapYear(year))
				return 29;
			else if(month==2 && !isLeapYear(year))
				return 28;
			else
				return 30;
		}
	}
	public static void main(String args[])
	{
		System.out.println(isLeapYear(1000));
		System.out.println(daysInMonth(2,2024));
		System.out.println(isLeapYear(2024));
		System.out.println(daysInMonth(1,2024));
		
	}

}
