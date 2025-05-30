package com.example;
public class SumChecker {
	public static boolean checkSum(int a,int b,int c) {
		if((a+b)==c) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean checkerSum(float a,float b,float c) {
		if((a+b)==c) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean checkSum(double a,double b,double c) {
		if((a+b)==c) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void main(String arg[]) {
		System.out.println(checkSum(5, 6, 11));
		System.out.println(checkSum(5.3f,6.34f,4.65f));
		System.out.println(checkSum(5.3d,6.34d,4.65d));
	}
}
