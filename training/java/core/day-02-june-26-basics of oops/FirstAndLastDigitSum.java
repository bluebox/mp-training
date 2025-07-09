package day2;

public class FirstAndLastDigitSum {

	public static void main(String[] args) {
		System.out.println(sumFirstAndLastDigit(252));
		System.out.println(sumFirstAndLastDigit(257));
		System.out.println(sumFirstAndLastDigit(0));
		System.out.println(sumFirstAndLastDigit(5));
		System.out.println(sumFirstAndLastDigit(-10));
	}
	public static int sumFirstAndLastDigit(int number) {
		if (number<0) return -1;
		int firstDigit=number%10;
		int lastDigit=firstDigit;
		while (number>0) {
			lastDigit=number%10;
			number/=10;
		}
		return firstDigit+lastDigit;
	}
}
