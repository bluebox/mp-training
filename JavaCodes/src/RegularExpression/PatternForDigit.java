package RegularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternForDigit {

	public static void main(String[] args) {
		Pattern pattern=Pattern.compile("\\d{5}");
		Matcher matches=pattern.matcher("9701674944");
		while(matches.find()) {
			String match=matches.group();
			if(!match.isEmpty())System.out.println(match);
		}
		System.out.println("printing every elements");
		pattern=Pattern.compile("\\d");
		matches=pattern.matcher("9701674944");
		while(matches.find()) {
			System.out.println(matches.group());
		}
		System.out.println("finding a number and its next character");
		pattern=Pattern.compile("\\d.");
		matches=pattern.matcher("9701674944");
		while(matches.find()) {
			System.out.println(matches.group());
		}
		System.out.println("starting with a");
		pattern=Pattern.compile("[^abhi]");
		matches=pattern.matcher("anand abhi aa abcd naresh");
		while(matches.find()) {
			String match=matches.group();
			if(!match.isEmpty()) {
				System.out.println(match);
			}
		}
		pattern=Pattern.compile("[a-zA-Z]*");
		matches=pattern.matcher("anand abhi aa abcd naresh");
		while(matches.find()) {
			String match=matches.group();
			if(!match.isEmpty()) {
				System.out.println(match);
			}
		}
		pattern=Pattern.compile("[0-9]*");
		matches=pattern.matcher("anand123abhi374 naresh979");
		while(matches.find()) {
			String match=matches.group();
			if(!match.isEmpty()) {
				System.out.println(match);
			}
		}
		pattern=Pattern.compile("[^0-9]*");
		matches=pattern.matcher("anand123abhi374 naresh979");
		while(matches.find()) {
			String match=matches.group();
			if(!match.isEmpty()) {
				System.out.println(match);
			}
		}
	}

}
