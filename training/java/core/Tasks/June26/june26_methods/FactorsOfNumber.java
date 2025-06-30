package corejava.june26_methods;

import java.util.Scanner;

public class FactorsOfNumber {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number to find its factors");
		int number=sc.nextInt();
		printFactors(number);
		sc.close();
	}
	public static void printFactors(int number) {
		if(number<1) {
			System.out.println("Invalid Input");
		}
		else {
			for(int i=1;i<=number;i++) {
				if(number%i==0) {
					System.out.print(i+" ");
				}
			}
		}
	}

}
