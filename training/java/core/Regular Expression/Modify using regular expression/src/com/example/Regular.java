package com.example;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Regular {
	public static void main(String[] args) {
		String s="[A-Z][a-z ]*[.?!]";
		Pattern s1=Pattern.compile(s);
	    Matcher m = s1.matcher("The bike is red, and has flat tyres.");
		if(m.find()) {
			System.out.println(m.group());
		}
	}
}
