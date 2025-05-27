package samplecodes;

public class Temp {
	public static void main(String[] args) {
		int n=123456;
		int evenDigitSum=getEvenDigitSum(n);
		System.out.println(evenDigitSum);
	}
	public static int getEvenDigitSum(int i) {
		int sum=0;
		while(i!=0) {
			int rem=i%10;
			if(rem%2==0) {
				sum+=rem;
			}
			i=i/10;
		}
		return sum;
	}
//	public static boolean isPalindrome(int n) {
//		int sum=0;
//		int k=n;
//		while(n!=0) {
//			int rem=n%10;
//			sum=sum*10+rem;
//			n=n/10;
//		}
//		return sum==k;
//	}

}
