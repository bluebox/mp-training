package com.prana;

import java.util.function.Consumer;

public class Mini2 {

	
	public static String everySecondChar(String source) {
		StringBuilder returnVal = new StringBuilder();
		for (int i = 0; i < source.length(); i++) {
		if (i % 2 == 1) {
		returnVal.append(source.charAt(i));
		}
		}
		return returnVal.toString();
		}

	Consumer<String> printTheParts = sentence -> {
	    String[] parts = sentence.split(" ");
	    for (String part : parts) {
	        System.out.println(part);
	    }
	};
	
	public static void main(String args[]) {
		System.out.println(everySecondChar("Water Flow"));
	}
	
}
