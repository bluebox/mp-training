package com.dailybasics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static void main(String[] args) {
		
		String string = "Hello";
		String word = "Hello World!";
		
		Pattern pattern = Pattern.compile(string);
		Matcher matcher = pattern.matcher(word);
		
		if(matcher.find()) {
			System.out.println("Match Found ."+matcher.group());
		}else {
			System.out.println("No Match found .");
		}
		
		pattern = Pattern.compile("\\d");
		matcher = pattern.matcher("my String is 123");
		
		while(matcher.find()) {
			System.out.println("Match found %s".formatted(matcher.group()));
		}
		
		pattern = Pattern.compile("[a-zA-z0-9]+@gmail\\.com");
		matcher = pattern.matcher("saketh123@gmail.com");
		if (matcher.find()) {
			System.out.println("your Email id is : "+matcher.group());
		}
		
		pattern = Pattern.compile("[^aeiou]");
		matcher = pattern.matcher("saketh123@yahoo.com");
		
		while(matcher.find()) {
			System.out.println("The consonent : "+matcher.group());
		}
		
		pattern = Pattern.compile("\\w+{4}");
		matcher = pattern.matcher("my contact is (sakethm)-456-7800000000011111111111(915)-435-4563434");
		
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
		
		pattern=Pattern.compile("^saketh|coading$");
		matcher = pattern.matcher("saketh is coading");
		if(matcher.find()) {
			System.out.println(matcher.group());
		}
		pattern=Pattern.compile("\\beth");
		matcher = pattern.matcher("saketh is coading");
		if(matcher.find()) {
			System.out.println(matcher.group());
		}
		pattern = Pattern.compile("[a-zA-z]{3}");
		matcher = pattern.matcher("saketh123@gmail.com");
		if (matcher.find()) {
			System.out.println("your Email id is : "+matcher.group());
		}
		pattern = Pattern.compile("[a-z]{4}");
		matcher = pattern.matcher("my contact is (sakethm)-456-7800000000011111111111(915)-435-4563434");
		
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
		pattern=Pattern.compile("\\beth$");
		matcher = pattern.matcher("saketh is coading");
		if(matcher.find()) {
			System.out.println(matcher.group());
		}
		
		

	}

}
