package day7.isPalindromeNumber;
import java.util.*;

public class PalindromNumber {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1;
		System.out.println("Enter the number, enter -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
		System.out.println("Is the number "+t1+" palindrome "+IsPalindrome.checkPalindrome(t1));
		System.out.println("---------------------------");
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
