package day7.sumFirstAndLast;
import java.util.*;

public class SumFirstAndLast {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1;
		System.out.println("Enter the number and -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			if(t1<0) {
				System.out.println("Enter the positive number");
				continue;
			}
			System.out.println("sum of the first and last in "+ t1+" is " +SumEdge.addEdgeDigits(t1));
			
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
