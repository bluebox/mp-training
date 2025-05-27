import java.util.Scanner;
public class PalindromeNumber {
	
	public static boolean isPalindrome(int n) {
		if(n<0) {
			n=-1*(n);
		}
		int temp=n;
		int rem,rev=0;
		while(n>0) {
			rem=n%10;
			rev=rev*10+rem;
			n/=10;
		}
		if(temp==rev) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter the number : ");
		int n=scanner.nextInt();
		boolean ans=isPalindrome(n);
		if(ans) {
			System.out.println("The "+n+" is palindrome number");
		}
		else {
			System.out.println("The "+n+" is not palindrome number");
		}
		scanner.close();
	}

}
