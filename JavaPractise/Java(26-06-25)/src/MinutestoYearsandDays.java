
public class MinutestoYearsandDays {

	public static void main(String[] args) {
		printYearsAndDays(561600);
	}
	public static void printYearsAndDays(long minutes)
	{
		if(minutes<0)
		{
			System.out.println("invalid Value");
		}
		else
		{
			long years=minutes/525600;
			long rem=minutes%525600;
			long days=rem/1440;
			System.out.println(years+"y"+" and "+days+"d");
		}
	}

}

