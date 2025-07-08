package RegularExpressions;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static void main(String[] args) {
		String word="Hello, Word!";
		
		System.out.println(word.matches(word));
		
		String[] words= {"This bike is red. hi","Hello World."};
		
		Pattern pattern = Pattern.compile("[A-Z].*\\.$");
		
		for(String w:words) {
			System.out.println("("+w+") --> \n"+" Matches pattern ["+pattern+"] \n"+pattern.matcher(w));
			System.out.println("--------------------------------------------------------------");
		}
		
		String Data ="""
				This bike is red,and has flat tire.
				I love being a L.P.A student!
				Hello, friends and family: welcome!
				How are you,boy?
				""";
		String[] data =Data.split("\n");
		
		Pattern pattern1 = Pattern.compile("([A-Z]).+([.?!])");
		
		for(String d: data) {
			System.out.println("("+d+") --> \n"+" Matches pattern ["+pattern1+"] \n"+pattern1.matcher(d));
			System.out.println("--------------------------------------------------------------");
			
		}
		
		Matcher p= pattern1.matcher(Data);
		
		while(p.find()) {
			System.out.println("Found match: " + p.group(0) + " at index " + p.start());
			System.out.println("Found match: " + p.group(1) + " at index " + p.start());
			System.out.println("Found match: " + p.group(2) + " at index " + p.start());
		}
		System.out.println("--------------------------------------------------------------");

		String AlphNum="a22b3c4d5";
		
		Pattern Alphnum = Pattern.compile("(\\d+)");
		
		System.out.println(Alphnum.matcher(AlphNum));
		System.out.println("--------------------------------------------------------------");

		
	    Matcher matcher = Alphnum.matcher(AlphNum);
	    
	    while (matcher.find()) {
	        System.out.println("Found match: " + matcher.group(1) + " at index " + matcher.start());
	      }
		
	}

}
