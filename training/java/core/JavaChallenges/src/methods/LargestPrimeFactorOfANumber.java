package methods;

import java.util.Scanner;

public class LargestPrimeFactorOfANumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Number:");
		int a=sc.nextInt();
		int res=findLargestPrime(a);
		System.out.println(res);
	}
	public static int findLargestPrime(int a)
	{
		if(isPrime(a) && a<10)
		{
			return a;
		}
		for(int i=(a+1)/2;i>=2;i--)
		{
			if(isPrime(i))
			{
				if(a%i==0)
				{
					return i;
				}
			}
		}
		return -1;
	}
	public static boolean isPrime(int a)
	{
		for(int i=2;i<=(a+1)/2;i++)
		{
			if(a%i==0)
			{
				return false;
			}
		}
		return true;
	}

}
