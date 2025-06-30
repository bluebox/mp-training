package corejava.june26_methods;

import java.util.Scanner;

public class DigitSumChallenge {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number greater than or equal to 0");
		int n = sc.nextInt();
		int result = sumDigits(n);
		if(result < 0) {
			System.out.println("You passed an invalid value");
		}
		else {
			System.out.println("The sum of digits of the given number "+n+" is: "+result);
		}
		sc.close();
	}
	public static int sumDigits(int number) {
		if(number < 0)
			return -1;
		else {
			int sum = 0;
			while(number>0) {
				int rem = number%10;
				sum += rem;
				number /= 10;
			}
			return sum;
		}
	}

}
