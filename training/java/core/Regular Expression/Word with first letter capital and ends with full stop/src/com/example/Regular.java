package com.example;
import java.util.regex.Pattern;
public class Regular {
	public static void main(String[] args) {
		String s="[A-Z]([a-z]|[ ])*[.]";
		System.out.println(Pattern.matches(s, "Hello world."));
	}
}
