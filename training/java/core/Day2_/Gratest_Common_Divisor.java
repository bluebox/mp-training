package com.Day2_;

public class Gratest_Common_Divisor {
	public static void main(String args[]) {
		int first=55,second=77;
		 if (first < 10 || second < 10) {
		        System.out.println("Invalid");
		    }else {

		    while (second != 0) {
		        int temp = second;
		        second = first % second;
		        first = temp;
		    }

		    System.out.println(first);
	}
	}
}