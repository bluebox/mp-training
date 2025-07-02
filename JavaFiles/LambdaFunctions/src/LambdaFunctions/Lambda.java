package LambdaFunctions;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Lambda {

	public static void main(String[] args) {
		
		Consumer<String> printTheParts = new Consumer<String>() {
		    public void accept(String sentence) {
		        String[] parts = sentence.split(" ");
		        for (String part : parts) {
		            System.out.println(part);
		        }
		    }
		};
		
		Consumer<String> printTheParts1 = new Consumer<String>() {
		    public void accept(String sentence) {
//		        String[] parts = sentence.split(" ");
		        Arrays.asList(sentence.split(" ")).forEach(s -> System.out.println(s));
		    }
		};
		
		Function<String, String> everySecondCharLambda = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
		
        UnaryOperator<String> everySecondCharLambda1 = source -> {
            StringBuilder returnVal = new StringBuilder();
                for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
        
        Supplier<String> iLoveJava = () -> "I love Java";

        

        String supplierResult = iLoveJava.get();

        
        
        printTheParts.accept("This is an example");
        printTheParts.accept("---------");
        printTheParts1.accept("This is an example");
        everySecondChar("This is an example");
        
        System.out.println("---------");
        System.out.println("Using original method: " + everySecondChar("This is an example"));
        System.out.println("Using lambda: " + everySecondCharLambda.apply("This is an example"));
        System.out.println("Using lambda(Unary): " + everySecondCharLambda1.apply("1234567890"));

        String result = everySecondCharacter(everySecondCharLambda1, "1234567890");
        System.out.println("Using lambda(Unary) function method: " +result);
        System.out.println("Using Supplier interface: "+iLoveJava.get());
        System.out.println("Using Supplier to assign to Variable : "+supplierResult);

	
	}
	
	public static String everySecondChar(String source) {
		StringBuilder returnVal =new StringBuilder();
		
		for(int i=0; i<source.length();i++) {
			if(i%2==1) {
				returnVal.append(source.charAt(i));
			}
		}
		return returnVal.toString();
	}
	
	
	public static String everySecondCharacter(UnaryOperator<String> func, String value) {
		return func.apply(value);
}

}
