package day6.leapYear;
import java.util.*;

public class IsLeapYear {
public static void main(String args[]) {
	Scanner sc=new Scanner(System.in);
	LeapOrNot l=new LeapOrNot();
	int input=0;
	System.out.println("Enter the Year in the range 0 to 9999");
	while(true) {
		input=sc.nextInt();
		if(input==-1)
			break;
		if(input<0 || input>9999) {
			System.out.println("invalid year,enter valid year");
			continue;
		}
		//l.isLeapYear(input)? System.out.println(input+" is a leap year "):System.out.println("Not a leap year");
		System.out.println(input +(l.isLeapYear(input)?"is a leap year":"is not a leap year"));
		System.out.println("---------------------------");
		
	}
}
}
