package com.Day2_;

public class odd_number_sum {
	public static void main(String args[]) {
		System.out.println("sum of odd num in range is "+sumodd(5,10));
	}
	public static boolean odd(int n) {
		if(n%2!=0) {
			return true;
		}return false;
	}
	public static int sumodd(int low,int high) {
		int sum=0;
		if(low<0 || high <0 || low> high) {
			System.out.println("ENter the correct value");
			return 0;		}
		for(int i=low;i<=high;i++) {
			if(odd(i)) {
				sum+=i;
			}
		}
		return sum;
	}
}