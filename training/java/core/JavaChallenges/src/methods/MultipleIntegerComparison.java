package methods;

import java.util.Scanner;

public class MultipleIntegerComparison {
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Number:");
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		boolean res=hasSameLastDigit(a,b,c);
		System.out.println(res);
	}
	public static boolean hasSameLastDigit(int a,int b,int c)
	{
		String A=String.valueOf(a);
		String B=String.valueOf(b);
		String C=String.valueOf(c);
		a=A.charAt(A.length()-1)-'0';
		b=B.charAt(B.length()-1)-'0';
		c=C.charAt(C.length()-1)-'0';
		return a==b || b==c || c==a;
	}

}
