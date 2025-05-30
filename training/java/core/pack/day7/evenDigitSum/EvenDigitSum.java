package day7.evenDigitSum;
import java.util.*;

public class EvenDigitSum {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1;
		System.out.println("Enter the Number enter -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			if(t1<0) {
				System.out.println("enter valid numbers");
				continue;
			}
			System.out.println("sum of even digits of "+t1+" is "+DigitSum.evenSum(t1));
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
