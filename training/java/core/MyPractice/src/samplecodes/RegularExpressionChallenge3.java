package samplecodes;
import java.util.regex.*;
public class RegularExpressionChallenge3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] sentences=
			{"The bike is red , and has flat tires.","I love being a new L.P.A student.",
					"Hello, friends and family: Welcome!",
					"How are you,Mary?",
					"Temporary text 123 *8932@# ujiki,"
			}; 
		String regex="[A-Z][\\p{all}]+[.?!]";
		Pattern p=Pattern.compile(regex);
		for(String s:sentences) {
			var matcher=p.matcher(s);
			if(matcher.matches()) {
				System.out.println(s + " Matches with the given regular expression");
			}else {
				System.out.println(s+ " Does not matches with the given regular expression");
			}
		}
	}

}
