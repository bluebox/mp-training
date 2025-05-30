package methods;

import java.util.Scanner;

public class FactorsOfNumber {
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Number:");
		int a=sc.nextInt();
		printFactors(a);
	}
	public static void printFactors(int n)
	{
		
		System.out.println("Factors of a Number are:");
		for(int i=1;i<=(n+1)/2;i++)
		{
			if(n%i==0)
			{
				System.out.println(i);
			}
		}
		System.out.println(n);
	}

}
