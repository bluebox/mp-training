package day7.isPerfect;

import java.util.Scanner;

public class IsPerfectNumber {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1,t2;
		System.out.println("Enter the number enter -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			
			boolean res=CheckPerfect.findPerfect(t1);
			if(res)
				System.out.println(t1+" is a perfect number");
			else 
				System.out.println("not a perfect number");
			
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
