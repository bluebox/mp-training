import java.util.Scanner;
public class IdentifyPalindrome {
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter any number: ");
		int number = input.nextInt();
		
		if(isPalindrome(number) == true)
		{
			System.out.println("The given number is a palindrome");
		}
		else
		{
			System.out.println("The given number is not a palindrome");
		}
	}
	public static boolean isPalindrome(int number)
	{
		int reversedNumber = 0;
		int temp = number;
		while(temp != 0)
		{
			int digit = temp % 10;
			reversedNumber = reversedNumber * 10 + digit;
			temp /= 10;
		}
		if(reversedNumber == number)
		{
			return true;
		}
		return false;
	}
}

