package corejava.june26_methods;

import java.util.Scanner;

public class ConfirmimgPerfectNumbers {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number to check whether it is a perfect number or not");
		int number=sc.nextInt();
		if(isPerfectNumber(number)) {
			System.out.println("Given number is a perfect number");
		}
		else {
			System.out.println("Given number is not a perfect number");
		}
		sc.close();
	}
	public static boolean isPerfectNumber(int number) {
		if(number>0) {
			int sum=0;
			for(int i=1;i<number;i++) {
				if(number%i==0) {
					sum+=i;
				}
			}
			if(sum==number) {
				return true;
			}
		}
		return false;
	}
}
