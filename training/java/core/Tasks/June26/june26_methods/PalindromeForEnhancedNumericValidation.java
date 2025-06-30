package corejava.june26_methods;

import java.util.Scanner;

public class PalindromeForEnhancedNumericValidation {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a string to check whether it is a pallindrome or not");
		String str=sc.nextLine();
		System.out.print("Given string "+str+" is a "+(isPallindrome(str)?"Pallindrome":"Not a pallindrome"));
		sc.close();
	}
	public static boolean isPallindrome(String str) {
		String reverse="";
		if(str.charAt(0)=='-') {
			for(int i=str.length()-1;i>0;i--) {
				reverse+=str.charAt(i);
			}	
			return str.equals("-"+reverse);
		}
		else {
			for(int i=str.length()-1;i>=0;i--) {
				reverse+=str.charAt(i);
			}
			return str.equals(reverse);
		}
		
	}

}
