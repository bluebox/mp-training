package RegularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternClass {

	public static void main(String[] args) {
		  Pattern pattern=Pattern.compile("anand");
		  Matcher matcher=pattern.matcher("anandKumar");
		  boolean val=matcher.find();
		  System.out.println(val);
		  matcher=pattern.matcher("abhi");
		  val=matcher.find();
		  System.out.println(val);
		  pattern=Pattern.compile("[a]*");
		  matcher=pattern.matcher("abhi");
		  val=matcher.find();
		  System.out.println(val);
		  matcher=pattern.matcher("aabhi");
		  while(matcher.find()) {
			  String match=matcher.group();
			  if(!match.isEmpty())System.out.println(match);
		  }
	}

}
