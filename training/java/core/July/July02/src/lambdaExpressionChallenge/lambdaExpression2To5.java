package lambdaExpressionChallenge;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class lambdaExpression2To5 {
	public static void main(String[] args) {
		
		//Challenge 2
		UnaryOperator<String> everySecondCharacter = s ->  {
			StringBuilder returnVal = new StringBuilder();
			for(int i =0; i<s.length();i++) {
				if(i%2 == 1) {
					returnVal.append(s.charAt(i));
				}
			}
			return returnVal.toString();
		};
		
		//Challenge 3
		System.out.println(	everySecondChar("1234567890"));
		System.out.println( everySecondCharacter.apply("1234567890"));
		
		//Challenge 5
		System.out.println( everySecondCharacters(everySecondCharacter,"1234567890"));
		
		
	}
	
	//Challenge 4
	public static String everySecondCharacters(Function<String,String> func, String s) {
		
		return func.apply(s);
	}
	
	public static String everySecondChar(String source) {
		
		StringBuilder returnVal = new StringBuilder();
		
		for(int i =0; i<source.length();i++) {
			if(i%2 == 1) {
				returnVal.append(source.charAt(i));
			}
		}
		return returnVal.toString();
	}
	
}
