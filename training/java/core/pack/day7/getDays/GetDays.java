package day7.getDays;
import java.util.*;

public class GetDays {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1,t2;
		System.out.println("Enter the month and year enter -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			t2=sc.nextInt();
			System.out.println("no.of days in month "+t1+" in  "+t2+" is"+DaysInYear.getMonthDays(t1,t2));
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
