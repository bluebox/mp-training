public class CheckingPalendromeNumber {
	
	public static void main(String[] args) {
		
		System.out.println(isPalindrome(-1221));
		System.out.println(isPalindrome(707));
		System.out.println(isPalindrome(11212));
		
	}
	
	public static boolean isPalindrome(int number) {
		
		if(number < 0) {
			number = -number;
		}
		int temp = number;
		
		int reverseNumber = 0;
		
		while(temp > 0) {
			int lastDigit = temp % 10;
			reverseNumber = reverseNumber* 10 + lastDigit;
			temp = temp /10;
		}
		return (reverseNumber == number) ;
	}
}
