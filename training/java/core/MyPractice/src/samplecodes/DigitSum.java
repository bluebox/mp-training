package samplecodes;

public class DigitSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Sum of all the digits = "+sumDigits(12345));
	}
	public static int sumDigits(int n) {
		int sum=0;
		while(n!=0) {
			int rem=n%10;
			sum+=rem;
			n=n/10;
		}
		return sum;
	}

}
