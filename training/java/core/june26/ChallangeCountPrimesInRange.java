package com.tulasidhar.june26;

// Problem : In any given range find the first 3 primes and exit loop
public class ChallangeCountPrimesInRange {
	public static void main(String[] args) {
		int primeCounter = 0;
		int rangeStart = 100;
		int rangeEnd = 1000;
		
		for(int i = rangeStart ; i<=rangeEnd ; i++) {
			if(isPrime(i)) {
				System.out.println(i + " is Prime");
				primeCounter++;
			}
			if(primeCounter == 3) {
				break;
			}
		}
		
	}
	
	public static boolean isPrime(int number) {
		for(int i=2 ; i<number ; i++) {
			if(number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
