package dayjunfifth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChallenge3 {
	public static void main(String args[]) {
		String s = "I like red.";
		String s2 = "K hello world!";
		String s3 = "This is new?";
		
		String msg = "hello this is narutoo your name please";
		
		String ptr = "[A-Za-z]{7}";
		
		Pattern pattern1 = Pattern.compile(ptr);
		Matcher matcher_new = pattern1.matcher(msg);
		
		if(matcher_new.find()) {
			System.out.println("7 chars matching : "+ matcher_new.group());
		}
		
		String regex= "[A-Z][a-z ]+[\\?\\.!]";
		
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(s2);
		Matcher matcher1 = pattern.matcher(s);
		Matcher matcher2 = pattern.matcher(s3);


		
		if(matcher.find()) {
			System.out.println(matcher.group());

		}
		if(matcher1.find()) {
			System.out.println(matcher1.group());

		}
		if(matcher2.find()) {
			System.out.println(matcher2.group());

		}
		
		
	}
}
