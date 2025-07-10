package Day9;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularChallenges {
	public static void main(String[] args) {
		//challenge 1
		String str="Hello, World!";
		System.out.println("Hello, World!".matches(str));
		
		//challenge 2
		Pattern pat=Pattern.compile("[A-Z].*\\.$");
		List<String> strs=Arrays.asList("The bike is red.","I am new student.","hello world");
		for(var s:strs) {
			Matcher match=pat.matcher(s);
			System.out.println(s+" "+match.matches());
		}
		System.out.println("-".repeat(35));
		
		
		
		//challenge 3
		Pattern pat1=Pattern.compile("[A-Z].+[.!?]$");
		List<String> strs2=Arrays.asList("The bike is red, and has flat tires.",
				"I love being a new L.P.A student!","Hello friends , and family: Welxcome!");
		for(String s: strs2) {
			Matcher match1=pat1.matcher(s);
			System.out.println(s+" "+match1.matches());
		}
		
	}
}
