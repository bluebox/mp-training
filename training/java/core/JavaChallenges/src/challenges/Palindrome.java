package challenges;
import java.util.*;
public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Number:");
		int n=sc.nextInt();
		if(n<0)
		{
			n=Math.abs(n);//converting negative number to positive number
		}
		boolean res=isPalindrome(n);
		if(res)
		{
			System.out.println(n + " is a Palindrome");
		}
		else
		{
			System.out.println(n + " is not a Palindrome");
		}
	
	}
	public static boolean isPalindrome(int n)
	{
		int r=n;
		int temp=0;
		while(r>0)
		{
			temp=temp*10+r%10;
			r=r/10;
		}
		return n==temp;
	}

}
