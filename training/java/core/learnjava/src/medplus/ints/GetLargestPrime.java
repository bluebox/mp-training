package medplus.ints;

import java.util.Scanner;

public class GetLargestPrime {
	public static int getLargestPrime(int value) {
		if (value <=1) return -1;
		for (int i =value;i >=2;i--) {
			if (value %i==0) {
				if (isPrime(i)) return i;
			}
		}
		return -1;
	}
	public static boolean isPrime(int value) {
		for (int i =2;i < value;i++) {
			if (value%i==0) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number : ");
		int value = scanner.nextInt();
		System.out.println(value+" its largest prime factor : "+getLargestPrime(value));
		scanner.close();
	}

}
