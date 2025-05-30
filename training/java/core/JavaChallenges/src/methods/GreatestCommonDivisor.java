package methods;

import java.util.Scanner;

public class GreatestCommonDivisor {
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Number:");
		int a=sc.nextInt();
		int b=sc.nextInt();
		int gcd=getGCD(a,b);
		System.out.println("GCD of "+a+" "+b+" is " +gcd);
		
	}
	public static int getGCD(int a,int b)
	{
		if(b==0)
		{
			return a;
		}
		return getGCD(b,a%b);
	}
}
