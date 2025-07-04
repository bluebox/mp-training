package dev.tulasidhar.july2.methodandlambdachallenge;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class MethodAndLambda {
	public static UnaryOperator<String> addRandomMiddleInitial = firstName -> {
		firstName = firstName.toUpperCase();
		StringBuilder sb = new StringBuilder(firstName);
		Random random = new Random();
		char middleInitial = (char) (random.nextInt(26) + 'A');
		sb.append(" " + middleInitial + " ");
		for (char ch : firstName.toCharArray()) {
			sb.append(ch);
		}
		return sb.toString();
	};

	public static String[] applyTransformations(String[] names, List<UnaryOperator<String>> operations) {
		String[] results = new String[names.length];
		Arrays.fill(results, "");
		for (int i = 0; i < names.length; i++) {
			for (UnaryOperator<String> operation : operations) {
				results[i] += operation.apply(names[i])+" ";
			}
		}
		return results;
	}

	public static void main(String[] args) {
		String[] names = { "Dasu", "Tulasidhar", "Random" };

		List<UnaryOperator<String>> ops = Arrays.asList(String::toLowerCase,
				s -> s.substring(0, 1).toUpperCase() + s.substring(1), 
				addRandomMiddleInitial 
		);

		String[] results = applyTransformations(names, ops);

		for (String result : results) {
			System.out.println(result);
		}
	}
}
