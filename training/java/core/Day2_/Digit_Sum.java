package com.Day2_;

public class Digit_Sum {
	public static void main(String args[]) {
		
		System.out.println(sumDigit(17));
		System.out.println(sumDigit(58));
		System.out.println(sumDigit(-58));
		System.out.println(sumDigit(6000));

	}
	public static int sumDigit(int num) {
		if(num<1) {
			return 0;
		}
		int sum=0;
		while(num>0) {
			sum+=num%10;
			num/=10;
		}
		return sum;
		
	}
}