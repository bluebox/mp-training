package July2;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class LambdaChallenge3 {
	
	public static String everySecondCharacterFunction(Function<String,String> func, String input) {
		return func.apply(input);
	}
	
	public static String everySecondCharacterUnary(UnaryOperator<String> func, String input) {
		return func.apply(input);
	}
	
	public static void main(String[] args) {
		
		Function<String,String> everySecondChar = source ->{
			StringBuilder temp = new StringBuilder();
			for(int i=0;i<source.length();i++) {
				if(i%2==1) temp.append(source.charAt(i));
			}
			return temp.toString();
		};
		
		UnaryOperator<String> everySecondChar1 = source ->{
			StringBuilder temp = new StringBuilder();
			for(int i=0;i<source.length();i++) {
				if(i%2==1) temp.append(source.charAt(i));
			}
			return temp.toString();
		};
		
		String input = "abcdefghijklmnopqrstuvwxyz";
		System.out.println(everySecondCharacterFunction(everySecondChar, input));
		System.out.println(everySecondCharacterUnary(everySecondChar1, input));

	}
}
