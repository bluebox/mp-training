package RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniChallenge {
	public static void main(String[] args) {
	Pattern pattern=Pattern.compile("^[A-Z][a-z\\s]*[.]");
	String[] s={"The bike is red.","In am a new student.","hello World.","Who are you?"};
	System.out.println(s[0]);
	Matcher matcher=pattern.matcher(s[0]);
	Matcher matcher1=pattern.matcher(s[1]);
	Matcher matcher2=pattern.matcher(s[2]);
	Matcher matcher3=pattern.matcher(s[3]);
    boolean flag=matcher.find();
    System.out.println(matcher.group()+" "+matcher1.matches()+" "+matcher2.find()+" "+matcher3.matches());
	}
	
	
}
