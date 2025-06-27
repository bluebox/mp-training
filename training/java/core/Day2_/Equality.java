package com.Day2_;
public class Equality {
	public static void main(String[] args) {
		System.out.println(equal(1, 1, 1));
		System.out.println(equal(1, 2, 1));
		System.out.println(equal(0, 0, 0));
		System.out.println(equal(-1, -1, -1));
	}
	public static String equal(int input1, int input2, int input3) {
		if (input1 < 0 && input2 < 0 && input3 < 0) {
			return "The values " + input1 + ", " + input2 + ", " + input3 + " are INVALID (all are negative)";
		}
		if (input1 == input2 && input2 == input3) {
			return "The values " + input1 + ", " + input2 + ", " + input3 + " are EQUAL";
		}
		if (input1 != input2 && input2 != input3 && input1 != input3) {
			return "The values " + input1 + ", " + input2 + ", " + input3 + " are NOT EQUAL";
		}
		return "The values " + input1 + ", " + input2 + ", " + input3 + " are NEITHER completely equal NOR completely different";
	}
}
