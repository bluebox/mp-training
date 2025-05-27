package challenges;
import java.util.*;
public class NumberTowords {
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Number:");
		long n=sc.nextLong();
		String res=calculateString(n);
		System.out.println(res);
	}
	public static String calculateString(long l)
	{
		if(l==0)
		{
			return "Zero";
		}
		return solve(l);
	}
	public static String solve(long n)
	{
		if(n<10)
		{
			return lessthanten((int)n);
		}
		if(n<20)
		{
			return lessthantwenty((int) n);
		}
		if(n<100)
		{
			return lessthanhundred((int)n/10)+ (n%10!=0?lessthanten((int)n%10):"");
		}
		if(n<1000)
		{
			return solve(n/100)+" Hundred "+(n%100!=0?solve(n%100):"");
		}
		if(n<1000000)
		{
			return solve(n/1000)+" Thousand "+(n%1000!=0?solve(n%1000):"");
		}
		return solve(n/1000000)+" Million "+(n%1000000!=0?solve(n%1000000):"");
	}
	public static String lessthanten(int n)
	{
		switch(n)
		{
		case 1:return "One";
		case 2:return "Two";
		case 3:return "Three";
		case 4:return "Four";
		case 5:return "Five";
		case 6:return "Six";
		case 7:return "Seven";
		case 8:return "Eight";
		case 9:return "Nine";
		}
		return "";
	}
	public static String lessthantwenty(int n)
	{
		switch(n)
		{
		case 11:return "Eleven";
		case 12:return "Twelve";
		case 13:return "Thirteen";
		case 14:return "Fourteen";
		case 15:return "Fifteen";
		case 16:return "Sixteen";
		case 17:return "Seventeen";
		case 18:return "Eighteen";
		case 19:return "Nineteen";
		}
		return "";
	}
	public static String lessthanhundred(int n)
	{
		switch(n)
		{
		case 1:return "Ten";
		case 2:return "Twenty";
		case 3:return "Thirty";
		case 4:return "Fourty";
		case 5:return "Fifty";
		case 6:return "Sixty";
		case 7:return "Seventy";
		case 8:return "Eighty";
		case 9:return "Ninety";
		}
		return "";
	}

}
