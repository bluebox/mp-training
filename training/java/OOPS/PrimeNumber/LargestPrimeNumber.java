package PrimeNumber;

import java.util.Arrays;

public class LargestPrimeNumber {

	public static void main(String[] args) {
		int number = 217;
		int largestPrime = getLargestPrime(number);
		String text = largestPrime > 0 ? (largestPrime + " is largest Prime") : "invalid input";
		System.out.println(text);

	}

	public static int getLargestPrime(int number) {
		if (number < 0) {
			return -1;
		}
		int largestPrime = 1;
		int validNumber[] = new int[number];
		Arrays.fill(validNumber, 1);
		for (int i = 2; i * i < number; i++) {
			if (validNumber[i] == 1) {
				for (int j = i * i; j < number; j = j + i) {
					validNumber[j] = 0;
				}
			}
		}
		for (int i = 2; i < number; i++) {
			if (validNumber[i] == 1) {
				if (number % i == 0) {
					largestPrime = i;
				}
			}
		}

		return largestPrime;
	}

}
