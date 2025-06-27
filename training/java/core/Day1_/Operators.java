package com.Day1_Premitive_Types_and_Operators_and_Methods;
public class Operators {
	public static void main(String[]args) {
		double a = 20.00;
		double b = 80.00;
		double c = ((a+b)*100.00);
		System.out.println("The total sum is: " + c);
		double remainder = c%40.00;
		if (remainder == 0) {
			System.out.println("The Remainder is: Zero");
		} else {
			System.out.println("Got some remainder: " + remainder);
		}
	
	}
}