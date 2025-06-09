package stringMethods;

public class StringMethodsDemo {

	public static void main(String[] args) {
		
//		String Declaration using string literal
		String s= "Hello welcome to java programming";
		
//		String length method
		System.out.println("Length of the string '"+s+"' is "+s.length());
		
//		Printing a particular character at an index
		System.out.println("Character at 5th index is "+s.charAt(5));
		
//		Printing the substring of the main string from a particular index
		System.out.println("Substring from the index 9 is "+s.substring(9));
		
//		Printing the substring of the main string from and to particular indices 
		System.out.println("Substring from index 6 to index 15 is "+s.substring(6,15));
		
	//  String Declaration using new Keyword
		String s2=new String("Pleased to be here");
		
//		Concattenation of two strings
		System.out.println("The concatenated string is "+s.concat(s2));
		
//		Displaying the index of a particular word in the string
		System.out.println("Index of the word 'java' is "+s.indexOf("java"));
		
//		Displaying the index of a particular word or character from a particular index
		System.out.println("Index of the character 'e' after the 10th index is "+s.indexOf('e',10));
		
//		Checking if two strings are equal or not
		boolean t= "Hello".equals("HEllo");
		System.out.println("Both the strings are equal : "+t);
		t= "hEllo".equals("hEllo");
		System.out.println("Both the strings are equal : "+t);
		
//		Checking if two strings are equal with not considering it's case
		boolean f= "Hello".equalsIgnoreCase("HeLLo");
		System.out.println("Bothe the strings are equal : "+f);
		
//		Comparing two strings
		System.out.println("HellO".compareTo("HELLO")); 
		//It returns an integer value.
		//If the value is positive then the first string is greater than the second string
		//If the value is negative then the second string is greater than the first string
		// If the value is zero then both the strings are equal
		
//		Converting a string to lower case
		System.out.println("HEllo".toLowerCase());
		
//		Converting a string to uppercase
		System.out.println("Hello".toUpperCase());
		
//		Removing the extra spaces in a string
		System.out.println("       Hello World      ".trim());
		
//		Replacing a character in the string with another character
		System.out.println("Hexxo".replace('x','l'));
	}
}
