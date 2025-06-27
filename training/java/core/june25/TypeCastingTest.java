package com.tulasidhar.june25;

public class TypeCastingTest {
	public static void main(String args[]) {
		
		//notes: default data type for non decimal values is int
		//       Default data type for numbers with decimal places is double
		
		int convertedFromDouble = (int) 3.53d;
		System.out.println(convertedFromDouble);
		
		double convertedFromInt = (double) 56;
		System.out.println(convertedFromInt);
		
		//notes: Java also has automatic type casting done when one type of value is assigned to 
		//		 another type of variable
		
		int integer = 98;
		double decimal = integer;
		System.out.println(decimal);
		
	}
}
