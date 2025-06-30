package corejava.june26_methods;

import java.util.Scanner;

public class SumOfEvenDigits {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number to sum all the even digits in it");
		int num=sc.nextInt();
		System.out.println("Sum of even digits of "+num+" is "+getEvenDigitSum(num));
		sc.close();
	}
	public static int getEvenDigitSum(int number) {
		int sum=0;
		if(number<=0) {
			return number<0?-1:0;
		}
		while(number>0) {
			int rem=number%10;
			if(rem%2==0) {
				sum+=rem;
			}
			number/=10;
		}
		return sum;
	}

}
