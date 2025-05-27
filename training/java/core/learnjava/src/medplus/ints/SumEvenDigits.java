package medplus.ints;

import java.util.Scanner;

public class SumEvenDigits {
	public static int getEvenDigitSum(int number) {
		if (number < 0) return -1;
		int sum =0;
		while (number !=0) {
			if ((number%10)%2==0) {
				sum += (number%10);
				
			}
			number = number/10;
		}
		return sum;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number : ");
		int number = scanner.nextInt();
		if (getEvenDigitSum(number)==-1) System.out.println("\nInvalid Input");
		else System.out.printf("\nSum of Even digits in %d is %d",number,getEvenDigitSum(number));
		scanner.close();
	}

}
