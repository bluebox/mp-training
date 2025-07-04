package dev.tulasidhar.july2.minichallenge;

import java.util.function.Function;

public class LambdaChallenge2 {
	public static Function<String, String> everySecondChar = source -> {
		String res = "";
		for (int i = 1; i < source.length(); i += 2) {
			res += source.charAt(i);
		}
		return res;
	};
}
