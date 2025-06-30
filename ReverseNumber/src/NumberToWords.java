
public class NumberToWords {
	public static void numberTOWords(int number) {
		if(number<0) {
			System.out.println("invalid number entered");
		}
		int reverseNumber=reverse(number);
		int digitCount=getDigitCount(number);
		int reverseDigitCount=getDigitCount(reverseNumber);
		 
		while(reverseNumber!=0) {
			int digit=reverseNumber%10;
			printDigitAsWord(digit);
			reverseNumber/=10;
		}
		for(int i=0;i<(digitCount-reverseDigitCount);i++) {
			System.out.println("zero");
		}
		if(number==0) {
			System.out.println("zero");
		}
	}

	public static int getDigitCount(int number) {
		if(number<0)
			return -1;
		if(number>0)
			return 1;
		int count=0;
		while(number!=0) {
			count++;
			number/=10;
		}
		return count;
	}

	public static int reverse(int number) {
		int reversed=0;
		int sign=number<0?-1:1;
		number=Math.abs(number);
		while(number!=0) {
			int lastDigit=number%10;
			reversed=reversed*10+lastDigit;
			number/=10;
		}
		return sign*reversed;
	}
	
	public static void printDigitAsWord(int digit) {
		switch(digit) {
		case 0:
			System.out.println("zero");
			break;
		case 1:
			System.out.println("one");
			break;
		case 2:
			System.out.println("two");
			break;
		case 3:
			System.out.println("three");
			break;
		case 4:
			System.out.println("four");
			break;
		case 5:
			System.out.println("five");
			break;
		case 6:
			System.out.println("six");
			break;
		case 7:
			System.out.println("seven");
			break;
		case 8:
			System.out.println("eight");
			break;
		case 9:
			System.out.println("nine");
			break;
			default:
			System.out.println("invalid number");
			break;
		}
	}
	
}
