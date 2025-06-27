package com.tulasidhar.june25;

public class Challenge1 {
	public static void main(String args[]) {
		byte byteData = 123;
		short shortData = 3245;
		int intData = 1293805;
		
		long result = 50000L + 10 * (byteData + shortData + intData);
		
		System.out.println(result);
	}
}

/*
 * Problem Statement: 
 * Challenge is to create four new variables
 * 1. A byte variable , set it to any valid byte number
 * 2. A short variable, set it to any valid short number
 * 3. An int variable , set it to any valid integer number.
 * 
 * Lastly , create a variable of type long , make it equal to 50,000
 * plus 10 times the sum of the values of the first 3 variable names
 * in your expression to calculate the sum
 */