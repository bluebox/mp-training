package dayjunfifth;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexChallenge2 {
	
	public static void main(String args[]) {
		String s = "I like red";
		String s2 = "K hello world.";
		String regex= "[A-Z][a-z ]+\\.";
		
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(s2);
		Matcher matcher1 = pattern.matcher(s);

		
		if(matcher.find()) {
			System.out.println(matcher.group());

		}
		if(matcher1.find()) {
			System.out.println(matcher1.group());

		}
		
		
	}

}
