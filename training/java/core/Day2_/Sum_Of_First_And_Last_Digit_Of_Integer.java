package com.Day2_;

public class Sum_Of_First_And_Last_Digit_Of_Integer {
	public static void main(String args[]) {
		System.out.println(sumFirstLastDigit(159));
		System.out.println(sumFirstLastDigit(0));
		System.out.println(sumFirstLastDigit(168));
		System.out.println(sumFirstLastDigit(5996845));
		System.out.println(sumFirstLastDigit(151));

	}
	public static int sumFirstLastDigit(int num) {
		if(num<0) {
			return -1;
		}
		int sum=0;
		sum+=num%10;
		while(num>=9) {
			num/=10;
		}
		sum+=num;
		return sum;
	}
}