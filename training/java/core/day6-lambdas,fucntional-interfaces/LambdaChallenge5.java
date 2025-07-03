package day6;

import java.util.function.UnaryOperator;

public class LambdaChallenge5 {
	public static UnaryOperator<String> everySecondChar = source -> {
		StringBuilder returnVal = new StringBuilder();
		for (int i = 1; i < source.length(); i += 2) {
			returnVal.append(source.charAt(i));
		}
		return returnVal.toString();
	};

	public static String everySecondCharacter(String word) {
		return everySecondChar.apply(word);
	}
}
