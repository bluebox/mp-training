package Practice.july7_regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class WordOccurence {
	   public static void main(String args[]) throws Exception {
	      String candidate = "this is a aasdaaatest, A TEST.";
	      String regex = "\\ba\\w*\\b";
	      Pattern p = Pattern.compile(regex);
	      Matcher m = p.matcher(candidate);
	      
	      String val = null; 
	      System.out.println("INPUT: " + candidate);
	      System.out.println("REGEX: " + regex + "\r\n");
	      
	      while (m.find()) {
	         val = m.group();
	         System.out.println("MATCH: " + val);
	      }
	      if (val == null) {
	         System.out.println("NO MATCHES: ");
	      }
	}
}
