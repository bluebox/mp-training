package RegexMiniChallanges;

import java.util.List;

public class Challenge3 {
	public static void main(String[]arg) {
		

	String challenge3 = "^[A-Z].*[.?!]$";{

    for (String s : List.of("The bike is red, and has punchered tires.",
            "I love being as a kid!",
            "Hello, all!",
            "How are you, Aasritha.",
            "H.")) {
        boolean matched = s.matches(challenge3);
        System.out.println(matched + ": " + s);
    }
}
}
}
