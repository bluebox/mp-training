package day7.printFactors;

import java.util.Scanner;

public class PrintFactors {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1;
		System.out.println("Enter Number enter -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			
			Factors.printFacts(t1);
			System.out.println("-------------------");
			
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
