package june26_methods;
import java.util.Scanner;

public class IsPalindrome {
	
	public static boolean isPalindrome(int num) {
		if (num < 0) 
			num = Math.abs(num);   		int original = num;        
		int rev = 0;

		while (num > 0) {
			int r = num % 10;
			rev = rev * 10 + r;    
			num /= 10;
		}
		return (original == rev);  
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number to check whether it's palindrome or not:");
		int num = sc.nextInt();

		System.out.println("The number is : " + (isPalindrome(num) ? "Palindrome" : "Not Palindrome"));
		sc.close();
	}
}
