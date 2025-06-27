package com.Day2_;

public class Sum_Of_All_Even {
	public static void main(String args[]) {
		System.out.println(sumOfEvenDigits(978356));
		System.out.println(sumOfEvenDigits(0));
		System.out.println(sumOfEvenDigits(13579));

	}
	public static int sumOfEvenDigits(int num) {
		if(num<0) {
			return -1;
		}
		int sum=0;
		while(num>0) {
			int rem=num%10;
			if(rem%2==0) {
				sum+=rem;
			}
			num=num/10;
		}
		return sum;
	}
}