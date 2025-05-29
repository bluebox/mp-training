package day6.minutesToYears;
import java.util.*;

public class MinutesToYears {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		long t=0;
		System.out.println("Enter the minutes ");
	try{
		while(true) {
			t=sc.nextLong();
			if(t==-1)
				break;
			
			if(t<0) {
				System.out.println("enter the valid time");
				continue;
			}
			YearConversion.minutesToYears(t);
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
