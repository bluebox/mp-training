package lambdaExpressionChallenge;

import java.util.Arrays;
import java.util.function.Consumer;

public class LambdaExpression1 {
	public static void main(String[] args) {
		
		Consumer<String> printTheParts1 = new Consumer<String>() {
			@Override
			public void accept(String t) {
				String[] parts = t.split(" ");
				for(String part: parts) {
					System.out.println(part);
				}
				
			}
			
		};
		
		Consumer<String> printTheParts2 = t-> Arrays.asList(t.split(" ")).forEach(System.out::println);
		
		System.out.println("Using Lambda Expression :");
		printTheParts2.accept("lets split this up");
		
		System.out.println("Using anonymous class :");
		printTheParts1.accept("lets split this up");
	}
}
