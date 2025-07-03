package lambdaExpressionChallenge;

import java.util.function.Supplier;

public class LambdaExpression6And7 {
	public static void main(String[] args) {
		
		//Challenge 6
		Supplier<String> iLoveJava =() -> "I Love Java";
		
		//Challenge 7 
		String result = iLoveJava.get();
		System.out.println(result);
	}
}
