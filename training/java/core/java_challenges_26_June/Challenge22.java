import java.util.*;
public class Challenge22 {
	
	public static boolean isPalindrome(int number)
	{
		int reverse=number;
		int ans=0;
		int i=0;
		while(number>0)
		{
			int rem=number%10;
			ans=ans+(int)(rem*Math.pow(10,i));
			i++;
			number=number/10;
			
		}
		if(ans==reverse)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPalindrome(131));
		System.out.println(isPalindrome(13));

	}

}
