package day8.largestPrimeFactor;

import java.util.Scanner;

public class LargestPrimeFactor {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1;
		System.out.println("Enter the number");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			System.out.println(LargestPrime.getLargestPrime(t1));
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
