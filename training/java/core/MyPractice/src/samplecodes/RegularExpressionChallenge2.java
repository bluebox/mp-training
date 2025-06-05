package samplecodes;
import java.util.regex.*;
public class RegularExpressionChallenge2 {
	public static void main(String[] args) {
		String[] sentences= {"My name is mohammed sameer.","and I'm from Karimnagar.","RCB won IPL 2025.","Become a DSA master","This is my code."};
		String regex="[A-z][a-z\\s]+\\.";
		// Should start with Capital letter followed by lower case and sentence should end with .(dot)
		
		Pattern p=Pattern.compile(regex);
		for(String s:sentences) {
			var matcher=p.matcher(s);
			if(matcher.matches()) {
				System.out.println(s+" ----> matches with the given regular expression");
			}else {
				System.out.println(s+" -----> does not matches with the given regular expression");
			}
		}
	}
}
