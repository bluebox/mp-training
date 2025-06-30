public class PalindromicNumbers {
	public static void main(String[] args) {
		System.out.println(isPalindrome(-1221));
		System.out.println(isPalindrome(707));
		System.out.println(isPalindrome(11212));
	}
	public static boolean isPalindrome(int number) {
		if (number<0) number=-number;
		int reverse=0;
		int original=number;
		while(number>0) {
			int digit=number%10;
			number/=10;
			reverse=10*reverse+digit;
		}
		return original==reverse;
	}
}