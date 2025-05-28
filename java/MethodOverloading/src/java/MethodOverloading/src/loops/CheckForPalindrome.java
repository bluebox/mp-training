package loops;

public class CheckForPalindrome {
	public static void main(String [] args) {
		int number = 12321;
		if(isPalindrome(number)) {
			System.out.println(number + " is a palindrome");
		}else {
			System.out.println(number + " is not a palindrome");
		}
		
	}
public static boolean isPalindrome(int number) {
	int temp = number;
	int remainder;
	int result = 0;
	while(temp>0) {
		remainder = temp %10;
		result = result * 10 + remainder;
		temp = temp/10;
	}
	if(result == number)
		return true;
	return false;
	
}
}
