package learn;

public class Palindrome {
	public static void main(String[] args) {
		int number=-49094;
		if(number<0) {
			System.out.print(number + "is it Palindrome " + isPalindrome(number*-1));
		}
		else 
		{
			System.out.print(number + " " + isPalindrome(number));
		}
	}
	
	public static boolean isPalindrome(int number) {
			int anotherNumber = 0;
			int copyNumber = number;
			while (copyNumber != 0) {
			anotherNumber = (anotherNumber * 10) + copyNumber % 10;
			copyNumber /= 10;
		}
		return anotherNumber == number;
	}
}
