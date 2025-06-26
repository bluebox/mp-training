package day7.gcd;

import java.util.Scanner;

public class GCD {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1,t2;
		System.out.println("Enter two numbers and  -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			t2=sc.nextInt();
			
			if(t1<10 || t2 <10) {
				System.out.println("invalid inputs try again");
				continue;
			}
			System.out.println("The GCD of the numbers are "+GcdOfNumbers.findGcd(Math.max(t1, t2),Math.min(t1,t2)));
            System.out.println("--------------------------------------------------");			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
