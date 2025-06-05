package patterns;

import java.util.regex.Pattern;

public class PatternMatching {
	public static void main(String[] args) {
		String[] strings = {"I am a student.", "The bike is red.", "Woooow!", "Where are you?", "hello World."};
		String sentencePattern = "^[A-Z][a-z]*[\s[a-z]]*[.]$";
		for(String string : strings) {
			System.out.println(Pattern.matches(sentencePattern,string)+": "+ string);
		}
	}
}
