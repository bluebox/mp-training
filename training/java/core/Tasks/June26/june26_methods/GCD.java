package corejava.june26_methods;

import java.util.Scanner;

public class GCD {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter any two numbers to find GCD");
		System.out.println("Enter the first number");
		int first=sc.nextInt();
		System.out.println("Enter the second number");
		int second=sc.nextInt();
		System.out.println("The gcd of two numbers is "+getGreatestCommonDivisor(first,second));
		sc.close();
	}
	
	public static int getGreatestCommonDivisor(int first,int second) {
		if( first>=10 || second >=10) {
			if(first>second) {
				for(int i=second;i>0;i--) {
					if(first%i==0)
						return i;
				}
			}
			else {
				for(int i=first;i>0;i--) {
					if(second%i==0)
						return i;
				}
			}
		}
		return -1;
	}
}
