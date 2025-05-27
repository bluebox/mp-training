package com.medplus;

public class EqualSumChecker {
	public static boolean hasequalsum(int value1,int value2 , int value3) {
		if ((value1 +value2) == value3 ) return true;
		return false;
	}
	public static void main(String[] args) {
		System.out.println(hasequalsum(1,1,1));
		System.out.println(hasequalsum(1,-1,0));
		System.out.println(hasequalsum(1,1,2));

	}

}
