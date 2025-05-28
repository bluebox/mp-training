package MinuteToYearAndDays;

public class MinutesToYearAndDaysProgram {

	public static void main(String[] args) {
		long minutes=561600;
		printYearAndDays(minutes);
	}
	public static void printYearAndDays(long minutes)
	{
		if (minutes < 0)
		{
			System.out.println("invalid value");
		}
		
		long days= (minutes/(60*24));
		long year=days/365,remainingDays=days%365;
		System.out.println(minutes + " minutes = "+ year + " years and "+remainingDays+" days");
	}

}
