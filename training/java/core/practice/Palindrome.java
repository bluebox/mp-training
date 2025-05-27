public class Palindrome {
	public static void main(String[] args) {
		//checking palindrome using a method
		int number=554555;
		
		if(isPalindrome(number)) {
		System.out.println("The given number "+number+" is Palindrome.");
		}
		else {
			System.out.println("The given number "+number+" is not a Palindrome.");
		}

	}
	public static boolean isPalindrome(int number) {
		boolean result;
		int original=number;
		int temp,sum=0;
		while(number>0) {
			temp=number%10;
			number=number/10;
			sum=(sum*10)+(temp);
		}
		if(original==sum) {
			result=true;
		}else {
			result=false;
		}
		
	return result;
	}

}
