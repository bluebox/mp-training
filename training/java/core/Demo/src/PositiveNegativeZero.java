package ExpressionsStatementsMethodOverloading;
import java.util.*;
import java.io.*;

public class PositiveNegativeZero {
	
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		int num=sc.nextInt();
		checkNumber(num);
	}
	
	public static void checkNumber(int num) {
		String ans="zero";
		if(num>0)  ans="positive";
		else if(num<0) ans="negative";
		System.out.println(ans);
	}

}
