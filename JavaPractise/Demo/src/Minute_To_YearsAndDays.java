import java.util.Scanner;

public class Minute_To_YearsAndDays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of minutes:");
		long minutes=sc.nextLong();
		printYearsAndDays(minutes);
		

	}
	public static void printYearsAndDays(long min) {
		long minutes=min;
		long years=minutes/525600;
		minutes=minutes%525600;
		long days=minutes/1440;
		System.out.println(min +" min = "+years+"y and "+days+"d");
	}

}
