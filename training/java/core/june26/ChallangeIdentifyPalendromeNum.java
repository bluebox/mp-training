package com.tulasidhar.june26;


public class ChallangeIdentifyPalendromeNum {

	
	public static void main(String[] args) {
		System.out.println(isPalindrome(-1221));
		System.out.println(isPalindrome(12121));
		System.out.println(isPalindrome(122321));
	}
	
    public static boolean isPalindrome(int number) {
        int originalNumber = number;
        int reverse = 0;

        // Make the number positive for processing
        number = Math.abs(number);

        while (number != 0) {
            int lastDigit = number % 10;
            reverse = reverse * 10 + lastDigit;
            number = number / 10;
        }

        return Math.abs(originalNumber) == reverse;
    }
}