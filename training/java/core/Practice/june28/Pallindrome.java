package Practice.june28;

import java.util.Scanner;

public class Pallindrome {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a string");
		String s=sc.nextLine();
		
		//to check if the given string has duplicate character
		System.out.println("Enter a charecter in the string, to check if the given string has dupicates of it");
		char c=sc.nextLine().charAt(0);
		if(s.indexOf(c)==-1) {
			System.out.println("String "+s+" does not contains the charecter "+c);
		}
		else if(s.indexOf(c)==s.lastIndexOf(c)) {
			System.out.println("String "+s+" has no duplicates with char "+c);
		}
		else {
			System.out.println("String has duplicates with char "+c);
		}
		
		//to check if the given string has same char at first and last
		if(s.charAt(0)==s.charAt(s.length()-1)) {
			System.out.println("Given String "+s+" starts and ends with same charecter");
		}
		else {
			System.out.println("Given String "+s+" starts and ends with different charecters");
		}
		
		//to check if the given string is palindrome or not
		String reverse=""; 
		for(int i=s.length()-1;i>=0;i--) {
			reverse+=s.charAt(i);
		}
		if(s.equals(reverse)) {
			System.out.println("Given String is a pallindrome");
		}
		else {
			System.out.println("Given string is not a pallindrome");
		}
		sc.close();
	}

}
