package com.tulasidhar.june26;

//Objective : to learn and test out method overloading

public class MethodOverloadingTest {
	
	public static void main(String args[]) {
		System.out.println("Sum of 1 and 2 = " + sum(1,2) );
		System.out.println("Sum of 1 , 2 and 3 = "+ sum(1, 2, 3));
		System.out.println("Sum of 1.2 and 3.4 = "+ sum(1.2, 3.4));
	}
	
	//called when 2 numbers are parameters
	public static int sum(int a, int b) {
		return a+b;
	}
	
	//called when 3 numbers are given 
	public static int sum(int a, int b, int c) {
		return a+b+c;
	}
	
	//called when 2 floating point numbers are parameters
	public static double sum(double a , double b) {
		return a+b;
	}
}
