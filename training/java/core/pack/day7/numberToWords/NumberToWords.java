package day7.numberToWords;

import java.util.Scanner;

public class NumberToWords {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1;
		System.out.println("Enter the number enter negative value to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1<0)
				break;
			
			WordOfNumber.numberToWord(t1);
			System.out.println("-----------------------------------------------");
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
