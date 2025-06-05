package RegularExpression;

import java.util.regex.*;

public class RegularExpressionChallenge {

	public static void main(String[] args) {
		 Pattern pat=Pattern.compile("\\b[A-Z][^\\.]*\\.");
		 Matcher mat=pat.matcher("The bike is red. or I am a new student.");
		 while(mat.find()) {
			 String match=mat.group();
			 if(!match.isEmpty())System.out.println(match);
		 }
	}

}
