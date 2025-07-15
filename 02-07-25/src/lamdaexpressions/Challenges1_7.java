package lamdaexpressions;


	import java.util.function.*;

	public class Challenges1_7 {

	    public static void main(String[] args) {

	        // Challenge 1
	        Consumer<String> printTheParts = sentence -> {
	            String[] parts = sentence.split(" ");
	            for (String part : parts) {
	                System.out.println(part);
	            }
	        };

	        printTheParts.accept("Lambda expressions are cool");

	        // Challenge 2
	        UnaryOperator<String> everySecondChar = source -> {
	            StringBuilder returnVal = new StringBuilder();
	            for (int i = 0; i < source.length(); i++) {
	                if (i % 2 == 1) {
	                    returnVal.append(source.charAt(i));
	                }
	            }
	            return returnVal.toString();
	        };

	        // Challenge 3
	        System.out.println(everySecondChar.apply("1234567890"));

	        // Challenge 4 & 5
	        String result = everySecondCharacter(everySecondChar, "1234567890");
	        System.out.println(result);

	        // Challenge 6 & 7
	        Supplier<String> iLoveJava = () -> "I love Java";
	        String supplierResult = iLoveJava.get();
	        System.out.println(supplierResult);
	    }

	    // Challenge 4
	    public static String everySecondCharacter(UnaryOperator<String> function, String value) {
	        return function.apply(value);
	    }
	}
