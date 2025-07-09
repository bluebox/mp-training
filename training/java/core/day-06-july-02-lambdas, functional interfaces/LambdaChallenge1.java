package day6;

import java.util.function.Consumer;

public class LambdaChallenge1 {
	static Consumer<String> printTheParts = sentence -> {
		String parts[] = sentence.split(" ");
		for (String part : parts) {
			System.out.println(part);
		}
	};
}
