package RegexMiniChallanges;

import java.util.List;

public class Challenge2 {
	public static void main(String[]args) {
	String challenge2 = "[A-Z][a-z\\s]+[.]";{
    for (String s : List.of("My fav colour is maiami blue.",
            "I am a new student.",
            "Hello world.",
            "How are you?")) {
        boolean matched = s.matches(challenge2);
        System.out.println(matched + ": " + s);
    }
	}
	}
}