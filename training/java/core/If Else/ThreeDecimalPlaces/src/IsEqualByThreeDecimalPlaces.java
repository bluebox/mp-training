package com.example;
public class IsEqualByThreeDecimalPlaces {
	public static boolean isEqualByThreeDecimalPlace(double x,double y) {
		int a=(int)(x*1000);
		int b=(int)(y*1000);
		if(a==b) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void main(String arg[]) {
		System.out.print(isEqualByThreeDecimalPlace(5.432,5.4319));
	}
}
