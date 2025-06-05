package String;

import java.util.Scanner;

public class MyString {
	public static void main(String [] args) {
		System.out.println("Some String operations are");
		Scanner sc =new Scanner(System.in);
		String myString = sc.next();
		System.out.println("The length of string is:"+myString.length());
		for(int i=0; i< myString.length(); i++) {
			System.out.println(myString.charAt(i));
			
		}
		String myString2 = sc.next();
		System.out.println("the concatination of "+myString+" and "+ myString2 +"is "+myString.concat(myString2));
		System.out.println(myString.contains("he"));
		String string1="Hello";
		String string2="hello";
		System.out.println(string1.equals(string2));
		System.out.println(string1.indexOf(string2));
		System.out.println(string1.replace('e','a'));
		System.out.println(string1.replaceAll(string1, "Hiiii"));
		System.out.println(string1.substring(1, 4));
		System.out.println(string1.isEmpty());
		System.out.println(string1.endsWith(string2));
		System.out.println(string1.startsWith(string1, 2));
		System.out.println(string1.toLowerCase());
		System.out.println(string1.toUpperCase());
		System.out.println(string1.join(string2));
		
	}

}
