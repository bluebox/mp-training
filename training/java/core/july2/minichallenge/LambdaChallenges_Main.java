package dev.tulasidhar.july2.minichallenge;

import java.util.function.Supplier;

public class LambdaChallenges_Main {

	public static void main(String[] args) {
		LambdaChallenge1.printTheParts.accept("this is java");
		System.out.println(LambdaChallenge2.everySecondChar.apply("pokemonAsh"));
		System.out.println(LambdaChallenge3.everySecondChar.apply("appdddfff"));
		System.out.println(LambdaChallenge4.everySecondCharacter("1234567890"));
		String result = LambdaChallenge5.everySecondCharacter("1234567890");
		System.out.println(result);
		 
		
		Supplier<String> iLovejava = LambdaChallenge6.iLoveJava;

		String supplierResult = LambdaChallenge7.iLoveJava.get();
		System.out.println(supplierResult);
	}

}
