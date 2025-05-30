package userinput;


import java.util.*;
public class UserInputChallenge {
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		int count=1;
		int sum=0;
		while(count<=5)
		{
			System.out.println("Enter Number #"+count+":");
			String s=sc.nextLine();
			try
			{
				int n=Integer.parseInt(s);
				sum+=n;
				count++;
			}
			catch(Exception e)
			{
				System.out.println("Enter the Valid Number:");
			}
		}
		System.out.println(sum);
	}

}
