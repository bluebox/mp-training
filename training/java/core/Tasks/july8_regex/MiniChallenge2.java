package corejava.july8_regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniChallenge2 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Pattern myPattern=Pattern.compile("^[A-Z][a-z\s]*[.]$");
		System.out.println("Enter the string that starts with a capital letter and ends with '.'");
		Matcher myStringMatcher=myPattern.matcher(sc.nextLine());
		if(myStringMatcher.find())
			System.out.println("You Entered correctly");
		else
			System.out.println("You didn't followed the rules while entering the string");
		sc.close();
	}

}
