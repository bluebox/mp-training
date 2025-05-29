package javaPrograms;
import java.util.*;
public class DigitCount {
	public static int getDigitCount(int n) {
		if(n<0) {
			return -1;
		}
		String s=Integer.toString(n);
		int len=s.length();
		return len;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the number : ");
		int n=sc.nextInt();
		int ans=getDigitCount(n);
		System.out.println("The digit count of "+n+" is : "+ans);
		sc.close();
	}

}
