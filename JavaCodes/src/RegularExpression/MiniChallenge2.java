package RegularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniChallenge2 {

	public static void main(String[] args) {
		 Pattern pat=Pattern.compile("\\b[A-Z][^\\.]*[^\\p{Punct}]");
		 Matcher mat=pat.matcher("The bike is red, or I am a new student.");
		 while(mat.find()) {
			 String match=mat.group();
			 if(!match.isEmpty())System.out.println(match);
		 }
	}

}
