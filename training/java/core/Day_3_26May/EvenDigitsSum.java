package Day_3_26May;

import java.util.Scanner;

public class EvenDigitsSum {
	public static int evenDigitsSum(int input) {
		if (input < 0)
			input = input * -1;
		int temp = input;
		int sum = 0;
		while (temp > 0) {
			int remainder = temp % 10;// gives last digit
			if (remainder % 2 == 0)
				sum += remainder;
			temp /= 10;

		}
		return sum;

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		int sumOfEvenDigits = evenDigitsSum(input);

		System.out.println("Sum of Even digits is " + sumOfEvenDigits);

		sc.close();
	}

}
