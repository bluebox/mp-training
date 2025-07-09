package day9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChallenge3 {
	public static void main(String[] args) {
		Pattern p = Pattern.compile("^[A-Z].*[.!?]");
		String[] phrases = new String[] { "The bike is red, and has flat tires.", "I love being a new L.P.A student!",
				"Hello, friends and family: Welcome!", "How are you, Mary?" };
		for (String phrase : phrases) {
			Matcher m = p.matcher(phrase);
			System.out.println(m.find() + " -> " + phrase);
		}
	}
}
