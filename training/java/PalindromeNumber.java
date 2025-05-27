import java.util.*;
public class PalindromeNumber {
	public static boolean isPalindrome(int num) {
		int a=num,b=0;
		int sign=1;
		if(num<0) sign=-1;
		while(a>0) {
			a=a*10+(a%10);
			a/=10;
		}
		return num==b*sign;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		if(isPalindrome(num)) {
			System.out.print("Palindrome");
		}
		else{
			System.out.print("Not a Palindrome");
		}
	}

}
