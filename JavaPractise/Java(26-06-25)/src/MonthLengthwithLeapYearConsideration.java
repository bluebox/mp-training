
public class MonthLengthwithLeapYearConsideration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean res=IsLeapYear(2017);
		System.out.println(res);
		System.out.println(getDaysInMonth(2,2020));

	}
	public static boolean IsLeapYear(int year)
	{
		if(year>=1 && year<=9999)
		{
			if((year%4==0&&year%100!=0)||year%400==0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	public static int getDaysInMonth(int month,int year)
	{
		if(month<1||month>12||year<1||year>9999)
		{
			return -1;
		}
		else
		{
			switch(month)
			{
			case 1:return 31;
			case 2:if(IsLeapYear(year))
			{
				return 29;
			}
			else
			{
				return 28;
			}
			case 3:return 31;
			case 4:return 30;
			case 5:return 31;
			case 6:return 30;
			case 7:return 31;
			case 8:return 31;
			case 9:return 30;
			case 10:return 31;
			case 11:return 30;
			case 12:return 31;
			default:return -1;
			}
		}
		
	}
	

}

