package RegularExpresions;

import java.util.List;

public class challenge3 {
	public static void main(String[] args) {
		String challenge3 = "^[A-Z]+[.?!]";
		for (String s:List.of("The bike is red,and has flat tiers",
				"I love being a new L.P.A student!",
				"Hello,friends and family.Welocome!",
				"How are you,Mary?"
				
				)) {
		boolean matched = s.matches(challenge3);
		System.out.println(matched+ ": " +s);
		
	}

	}
}
