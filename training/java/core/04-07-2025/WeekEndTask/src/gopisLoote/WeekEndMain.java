package gopisLoote;

import java.math.BigInteger;
import java.util.Scanner;

public class WeekEndMain {
	public static BigInteger compute(BigInteger val)
	{ 
		
		if(val.equals(0) )
	       {
		return BigInteger.ZERO;
	       }
		else {
	return BigInteger.ONE.add(val.subtract(BigInteger.ONE).mod(BigInteger.valueOf(9)));
		}
	}
public static void main(String []args)
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter a non-negative number :");
	String num=sc.nextLine();
	
	 if(!num.matches("\\d+")) 
	 {
		System.out.println(" Invalid number");
		return;
	}
	BigInteger val=new BigInteger(num);
	System.out.println("Digital root of given number is: "+compute(val));
	
	
	
	
	
}
}
