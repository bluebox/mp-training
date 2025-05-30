package methods;

import java.util.Scanner;

public class PerfectNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Number:");
		int a=sc.nextInt();
		boolean res=isPerfectNumber(a);
		System.out.println(res);
	}
	public static boolean isPerfectNumber(int a)
	{
		int sum=0;
		for(int i=1;i<=(a+1)/2;i++)
		{
			if(a%i==0)
			{
				sum+=i;
			}
		}
		return sum==a;
	}

}
