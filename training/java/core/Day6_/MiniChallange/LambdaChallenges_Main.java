package MiniChallange;

import java.util.function.Supplier;

public class LambdaChallenges_Main {

	public static void main(String[] args) {
		System.out.println("Hello All Welcome To Hyderabad");
		MiniChallenge1.printTheParts.accept("Hello All Welcome To Hyderabad");
		System.out.println(MiniChallenge2.everySecondChar.apply("Hello All Welcome To Hyderabad"));
		System.out.println(MiniChallenge3.everySecondChar.apply("9949592611"));
		System.out.println(MiniChallenge4.everySecondCharacter("8919802996"));
		String result = MiniChallenge5.everySecondCharacter("9573177110");
		System.out.println(result);
		//Challenge 6, this doesnt do anything
		Supplier<String> iLovejava = MiniChallenge6.iLoveJava;

		String supplierResult = MiniChallenge7.iLoveJava.get();
	    System.out.println(supplierResult);
	}

}