package July2;

import java.util.function.Supplier;

public class LambdaChallenge4 {
	public static void main (String[] args) {
		Supplier <String> iLoveJava = () -> "I Love Java";
		System.out.println(iLoveJava.get());
	}
}
