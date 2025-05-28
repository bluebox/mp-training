package samplecodes;

public class Palindrome {
	public static void main(String[] args) {
		int n=-121;
		if(isPalindrome(n)) {
			System.out.println("Given number "+n+" is Palindrome");
		}else {
			System.out.println("Given number "+n+" is Not a Palindrome");
		}
	}
	public static boolean isPalindrome(int n) {
		int sum=0;
		int k=n;
		while(n!=0) {
			int rem=n%10;
			sum=sum*10+rem;
			n=n/10;
		}
		return sum==k;
	}
}
