package javaPrograms;

import java.util.Scanner;
public class DigitSum {
	
	public static int getDigitsSum(int n) {
		int rem=0,sum=0;
		while(n>0) {
			rem=n%10;
			sum+=rem;
			n/=10;
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter the number : ");
		int n=scanner.nextInt();
		if(n<0) {
			System.out.println("The number must greater than zero or positive");
		}
		else {
			int ans=getDigitsSum(n);
			System.out.println("The didgits sum of "+n+" is : "+ans);
		}
		scanner.close();
	}

}
