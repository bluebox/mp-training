package corejava.june26_methods;

import java.util.Scanner;

public class SumFirstLastDigit {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number to sum its first and last digits");
		int num=sc.nextInt();
		System.out.println("Sum of forst and last digits of the number "+num+" is: "+sumFirstLastDigit(num));
		sc.close();
	}
	public static int sumFirstLastDigit(int number) {
		if(number<=0) {
			return number<0?-1:0;
		}
		else if(number/10==0) {
			return number+number;
		}
		int last=number%10;
		while(number>=10) {
			number/=10;
		}
		int first=number;
		return first+last;
	}
}
