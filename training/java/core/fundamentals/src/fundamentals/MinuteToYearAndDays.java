package fundamentals;

public class MinuteToYearAndDays {
	public static void main(String args[])
	{
		printYearAndDays(561600);
	}
	static void printYearAndDays(int minutes)
	{
		int year=minutes/(60*24*365);
		int days=(minutes%(60*24*365))/(60*24);
		System.out.println(minutes+" min = "+year+"y and "+days+"d");
	}
}