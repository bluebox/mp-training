package medplus.ints;

import java.util.Scanner;

public class IdentifyPalindrome {
	
	
	public static boolean ispalindrome(int value) {
		int reversed =0;
		int duplicate = value;
		while (value !=0){
			reversed = (reversed*10)+(value%10);
			value /=10;
		}
		if (reversed == duplicate) return true;
		return false;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a number : ");
		int value = scanner.nextInt();
		if (ispalindrome(value)) System.out.println("it is a palindrome .");
		else System.out.println("Its not a palindrome . ");
		scanner.close();
	}

}
