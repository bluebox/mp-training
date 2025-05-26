package com;

public class HelloWorld {
	public static void main(String[] args) {
		printDetails(Integer.valueOf(12), "Mourya");
		printDetails(10, "Surya"); // 
	}
	
	public static void printDetails(Integer num, String name) {
		System.out.println("The Student details with roll " + num + " is " + name);
	}
	
	public static void printDetails(int num, String name) {
		System.out.println("The Student details with roll number " + num + " is " + name);
	}
}