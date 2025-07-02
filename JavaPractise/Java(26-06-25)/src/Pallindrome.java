
public class Pallindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ispallindrome(121));

	}
	public static boolean ispallindrome(int num)
	{
		if(num<0)
		{
			num=Math.abs(num);
		}
		int num1=num;
		int rev=0;
		while(num!=0)
		{
			int rem=num%10;
			rev=rev*10+rem;
			num=num/10;
		}
		if(rev==num1)
			return true;
		else
			return false;
	}

}

