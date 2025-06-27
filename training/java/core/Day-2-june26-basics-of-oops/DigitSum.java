package day_2_june26_basics_of_oops;

public class DigitSum {

	public static void main(String[] args) {
		System.out.println(sumDigits(-4));
		System.out.println(sumDigits(125));
		System.out.println(sumDigits(1000));
	}
	public static int sumDigits(int number) {
		if (number<0) return -1;
		int sum=0;
		while(number>0) {
			int remainder=number%10;
			number=number/10;
			sum+=remainder;
		}
		return sum;
	}
}
