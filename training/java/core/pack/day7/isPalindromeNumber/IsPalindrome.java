package day7.isPalindromeNumber;

public class IsPalindrome {
public  static boolean  checkPalindrome(int num) {
	int temp=num,res=0,rem;
	temp=Math.abs(temp);
	while(temp>0) {
		 rem=temp%10;
		res=res*10+rem;
		temp=temp/10;
		
	}
	System.out.println(res);
	temp=(num<0)?res*-1:res;
	return res==num;
}
}
