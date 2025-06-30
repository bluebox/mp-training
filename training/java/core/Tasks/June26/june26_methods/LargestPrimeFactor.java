package corejava.june26_methods;

import java.util.Scanner;

public class LargestPrimeFactor {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number to find it's largest prime factor");
		int number=sc.nextInt();
		if(getLargePrime(number)>0) {
			System.out.print(getLargePrime(number)+"is the largest prime factor of the given number "+number);
		}
		else if(number>0){
			System.out.println("Your entered number has no prime factors");
		}
		else {
			System.out.println("You enetered an invalid input!!!");
		}
		sc.close();
	}
	public static int getLargePrime(int number) {
		if(number>1) {
			for(int n=number/2;n>=2;n--) {
				if(isPrime(n) && number%n==0) {
					return n;
				}
			}
		}
		return -1;
	}
	public static boolean isPrime(int n) {
		for(int i=2;i<=n/2;i++) {
			if(n%i==0)
				return false;
		}
		return true;
	}

}
