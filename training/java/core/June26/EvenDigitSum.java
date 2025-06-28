package June26;

public class EvenDigitSum {
	public static void main(String[] args) {
		System.out.println(getEvenDigitSum(123456789));
		System.out.println(getEvenDigitSum(252));
		System.out.println(getEvenDigitSum(-22));
	}
	public static int getEvenDigitSum(int number) {
		if(number < 0) return -1;
		int sum = 0;
		int counter = 1;
		int rev = reversefun(number);
		while( rev != 0) {
			if (counter % 2 == 0) sum += rev % 10;
			rev /= 10;
			counter++;
		}
		return sum;
	}
	public static int reversefun(int number) {
		int rev = 0;
		while(number != 0) {
			int lastDigit = number % 10;
			rev = rev*10 + lastDigit;
			number /= 10;
		}
		return rev;
	}
}
