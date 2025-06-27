package com.tulasidhar.june25;

/*
	Challenge 2
	Problem Statement:
	1.Create double variable with value of 20.00
	2.Create a second variable of type double with a value 80.00
	3.Add both numbers together , then multiply by 100.00
	4.Use the remainder operator, to figure out what the remainder from 
	  the result of the operations in step 3 , and 40.00 will be
	5.Create boolean variable that assigns the value true, if the remainder in step
	  four is 0.00, or false if it's not zero
	6.output the boolean variable just to see what the result is.
	7.Write an if-then statement that displays a message , "got some remainder" if the
	  boolean in step 5 is not true
*/

public class Challenge3 {
	public static void main(String args[]) {
		
		double num1 = 20.00d;
		double num2 = 80.00d;
		double sum = (num1+num2) * 100.00;
		
		double remainder = sum % 40.00d;
		
		boolean isDivisible = (remainder == 0.00d) ? true : false;
		System.out.println("value of the boolen: " + isDivisible);
		
		if(!isDivisible) {
			System.out.println("got some remainder");
		}
		
	}
}
