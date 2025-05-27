package challenges;

import java.util.Scanner;

public class EvenDigitSum {
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Number:");
		int n=sc.nextInt();
		int result=getEvenDigitSum(n);
		System.out.println("Even Digit Sum is:"+ result);
	}
	public static int getEvenDigitSum(int n)
	{
		if(n<0)
		{
			return -1;
		}
		int sum=0;
		String s=String.valueOf(n);
		for(int i=0;i<s.length();i++)
		{
			if((i+1)%2==0)
			{
				sum+=s.charAt(i)-'0';
			}
		}
		return sum;
	}

}
