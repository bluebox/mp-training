package challenges_8th_july;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class miniChallenge2 {
	public static void main(String[] args) {
		Pattern p=Pattern.compile("^[A-Z][a-z]*.$");
		String str="Hello!";
		Matcher m=p.matcher(str);
		System.out.println(m.find());
	}
}
