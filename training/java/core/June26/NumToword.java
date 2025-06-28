package June26;

public class NumToword {
	public static void main(String[] args) {
		
		System.out.println("Digit Counts :");
		System.out.println(getDigitCount(0));
		System.out.println(getDigitCount(123));
		System.out.println(getDigitCount(-12));
		System.out.println(getDigitCount(5200));
		
		System.out.println("Reversed Numbers :");
		System.out.println(reverse(-12));
		System.out.println(reverse(1212));
		System.out.println(reverse(1234));
		System.out.println(reverse(100));
		
		System.out.println("Number To Words :");
		System.out.println(numberToWords(123));
		System.out.println(numberToWords(1010));
		System.out.println(numberToWords(1000));
		System.out.println(numberToWords(-12));
	}
	public static String numberToWords(int number) {
		if(number < 0) return "Invalid Value";
		String word="";
		int rev = reverse(number);
		int remaining = getDigitCount(number) - getDigitCount(rev);
		while(rev != 0) {
			int lastDigit = rev % 10;
			word += helperfun(lastDigit);
			word += " ";
			rev /= 10;
		}
		word += "ZERO ".repeat(remaining);
		return word;
		
	}
	public static String helperfun(int number) {
		return switch(number) {
		case 0 -> "ZERO";
		case 1 -> "ONE";
		case 2 -> "TWO";
		case 3 -> "THREE";
		case 4 -> "FOUR";
		case 5 -> "FIVE";
		case 6 -> "SIX";
		case 7 -> "SEVEN";
		case 8 -> "EIGHT";
		case 9 -> "NINE";
		default -> "Invalid Value";
		};
	}
	public static int reverse(int number) {
		int rev = 0;
		while(number != 0) {
			int lastDigit = number % 10;
			rev = rev*10 + lastDigit;
			number /= 10;
		}
		return rev;
	}
	public static int getDigitCount(int number) {
		if(number == 0) return 1;
		int counter = 0;
		if(number < 0) return -1;
		while(number != 0) {
			counter ++;
			number /= 10;
		}
		return counter;
	}
}
