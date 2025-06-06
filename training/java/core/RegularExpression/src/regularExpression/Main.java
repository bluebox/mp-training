package regularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("an");
		Matcher matcher = pattern.matcher("5dgAnff");
		System.out.println(matcher.find());

		pattern = Pattern.compile(".+@.+", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher("5dgAn@ff");
		System.out.println(matcher.find());

		pattern = Pattern.compile(".+@.+", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher("5dgAn@");
		System.out.println(matcher.find());

		pattern = Pattern.compile(".+@.*", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher("5dgAn@");
		System.out.println(matcher.find());
		
		pattern=Pattern.compile("@gmail.",Pattern.LITERAL);
		 matcher =pattern.matcher("5dgAn@gmail.com");
		System.out.println(matcher.find());
		
		pattern=Pattern.compile("\\d");
		 matcher =pattern.matcher("53545");
		System.out.println(matcher.matches());
		
	}

}
