package Palindrome;

public class IsAPalindromeOrNot {

	public static void main(String[] args) {
		
		int number=232;
		System.out.println(number +" is a "+ (isPalindrome(number)?"palindrome":"Not a Palindrome"));

	}
	public static boolean isPalindrome(int number)
	{
		int revNumber=0,temp=number;
		while(temp>0)
		{
			revNumber =revNumber*10+temp%10;
			temp/=10;
		}
		return (revNumber == number);
	}

}
