package June26;

public class PalindromeNumber {
	public static void main(String[] args) {
		System.out.println(isPalindrome(-1221));
		System.out.println(isPalindrome(707));
		System.out.println(isPalindrome(11212));
	}
	public static boolean isPalindrome(int number) {
		int rev = reversefun(number);
		return (number == rev);
	}
	public static int reversefun(int number) {
		int rev = 0;
		while(number != 0) {
			int lastDigit = number % 10;
			rev = rev*10 + lastDigit;
			number /= 10;
		}
		return rev;
	}
}
