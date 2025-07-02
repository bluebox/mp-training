package com.prana;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Mini34567 {
	
    public static void main(String ar[]) {
    	System.out.println(everySecondChar.apply("1234567890")); 
    	String result = everySecondCharacter(everySecondChar, "1234567890");
    	System.out.println(result);
    	String supplierResult = iLoveJava.get();
    	System.out.println(supplierResult); 
    	
    }
	
	static UnaryOperator<String> everySecondChar = source -> {
		StringBuilder returnVal = new StringBuilder();
		for (int i = 0; i < source.length(); i++) {
		if (i % 2 == 1) {
		returnVal.append(source.charAt(i));
		}
		}
		return returnVal.toString();
		};
	
	
		static Supplier<String> iLoveJava = () -> "I love Java";
		
		public static String everySecondCharacter(Function<String, String> func, String input) {
		    return func.apply(input);
		}
}


