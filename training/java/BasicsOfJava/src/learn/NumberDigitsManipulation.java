package learn;

public class NumberDigitsManipulation {
	
	public static void main(String[] args) {
		numberToWords(202);
		}
	
	public static void numberToWords(int number) {
		
		int digitCount = getDigitCount(number);
		int reverseNumber = reverse(number);
		while(reverseNumber != 0 && digitCount!=0) {
			switch(reverseNumber % 10) {
				case 0 -> System.out.print(" zero ");
				case 1 -> System.out.print(" one ");
				case 2 -> System.out.print(" two ");
				case 3 -> System.out.print(" three ");
				case 4 -> System.out.print(" four ");
				case 5 -> System.out.print(" five ");
				case 6 -> System.out.print(" six ");
				case 7 -> System.out.print(" seven ");
				case 8 -> System.out.print(" eight ");
				case 9 -> System.out.print(" nine ");
			}
			reverseNumber /= 10;
			digitCount-=1;
			if(reverseNumber == 0) {
				System.out.println(" zero ".repeat(digitCount));
				digitCount = 0;
			}
		}
	}
	public static int reverse(int number) {
		
		int anotherNumber = 0;
		while (number != 0) {
			anotherNumber = (anotherNumber * 10) + number % 10;
			number /= 10;
		}
		return anotherNumber;
	}
	public static int getDigitCount(int number) {
		
		int count = 0;
		while(number != 0) {
			number/=10;
			count += 1;
		}
		return count;
	}
}
