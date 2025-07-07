package July2;

import java.util.function.Consumer;

public class LambdaChallenge {
	public static void main(String[] args) {

		Consumer<String> printWord = sentence -> {
			String[] parts = sentence.split(" ");
			for (String part : parts) {
				System.out.println(part);
			}
		};

		printWord.accept("I Love Java");
	}
}
