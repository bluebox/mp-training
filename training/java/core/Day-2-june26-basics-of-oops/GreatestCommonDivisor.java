package day_2_june26_basics_of_oops;

public class GreatestCommonDivisor {

	public static void main(String[] args) {
		System.out.println(gcd(12,3));
	}
	public static int gcd(int a,int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}
}
