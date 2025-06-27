package com.tulasidhar.june26;

public class ChallengeEqualityPrinter {
	public static void main(String[] args) {
		printEqual(1,1,1);
		printEqual(1,1,2);
		printEqual(-1,-1,-1);
		printEqual(1,2,3);
	}
	
	public static void printEqual(int a, int b, int c) {
		if(a < 0 || b<0 || c<0) {
			System.out.println("Invalid Value");
		}
		else if(a == b && b == c) {
			System.out.println("All numbers are equal");
		}
		//checking if we have atleast one difference and one similar pairs
		else if((a != b || b!=c || a!=c) && (a==b || b==c || a==c)) {
			System.out.println("Neither all are equal or different");
		}
		else {
			System.out.println("All numbers are different");
		}	
	}
}
