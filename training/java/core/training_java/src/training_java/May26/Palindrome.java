package training_java.May26;

public class Palindrome {
	public static boolean isPalindrome(int number) {
		int reverse=0;
		
		if(number<0) {
			number=-number;
		}
		int reference=number;
		while(number>0) {
			reverse=(reverse*10)+(number%10);
			number=number/10;
			
		}
		return reference==reverse;
	}
	
public static void main(String[] args) {
	
	System.out.println(isPalindrome(-1221));
	System.out.println(isPalindrome(11212));
	System.out.println(isPalindrome(767));
	
	
}
}
