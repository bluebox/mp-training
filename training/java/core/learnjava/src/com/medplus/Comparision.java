package com.medplus;

public class Comparision {
	
	public static boolean areEqualToThreeDecimals(double value1,double value2) {
		if (Math.round(value1*1000) == Math.round(value2*1000)) return true;
		return false;
	}
	
	public static void main(String[] args) {
		boolean check = areEqualToThreeDecimals(10.10111,901.09);
		System.out.println("The number equal upto 3 numbers : "+check);
		
		check = areEqualToThreeDecimals(10.10911,10.109);
		System.out.println("The number equal upto 3 numbers : "+check);
	}

}
