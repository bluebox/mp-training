package com.example;
public class IsLeapYear {
	public static boolean isLeapYear(int year) {
		if(year>0) {
			if((year%4)==0) {
				if((year%100)==0) {
					if((year%400)==0) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return true;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public static void main(String arg[]) {
		System.out.print(isLeapYear(-840));
	}
}
