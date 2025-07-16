package corejava.july8_regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniChallenge1 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a string to find the pattern: ");
		Pattern myPattern=Pattern.compile("Hello, World!",Pattern.LITERAL);
		Matcher myStringMatcher=myPattern.matcher(sc.nextLine()); 
		if(myStringMatcher.find())
			System.out.println("Pattern found in the given string");
		else
			System.out.println("Can't find Pattern in the given string");
		sc.close();
	}

}
