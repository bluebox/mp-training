package com.tulasidhar.june26;

//Problem: Given a range 1 to 1000 sum all the numbers that are divisible by 
//		   both 3 and 5 , break after finding 5 such numbers , print them and prin the sum

public class ChallengeSum3And5 {
	public static void main(String[] args) {
		int counter = 0;
		int sum = 0;
		for(int i=1 ; i<= 1000 && counter < 3; i++) {
			if(i % 3 == 0 && i % 5 == 0) {
				counter++;
				sum+=i;
				System.out.println(i+" is divisible by 3 and 5");
			}
		}
		System.out.println("Sum of such 3 numbers = " + sum);
	}
	
}
