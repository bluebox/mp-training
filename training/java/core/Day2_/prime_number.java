package com.Day2_;

public class prime_number {
	public static void main(String[] args) {
		int primeCount = 0;

		for (int i = 2; i <= 1000; i++) {
			if (isPrime(i)) {
				System.out.println(+ i);
				primeCount++;
				if (primeCount == 3) {
					break;
				}
			}
		}
	}

	public static boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		}

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}
}
