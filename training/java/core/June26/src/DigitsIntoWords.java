
public class DigitsIntoWords {

	public static void main(String[] args) {
		
		 numberToWords(123);
	     numberToWords(1010);
	     numberToWords(1000);
	     numberToWords(-12);
	}
	
	public static void numberToWords(int number) {
		
		if(number < 0) {
			System.out.println("Invalid Value");
			return;
		}
		
		int reverseNumber = reverse(number);
		int reverseCount = getDigitCount(reverseNumber);
		while(reverseNumber > 0) {
			int lastDigit = reverseNumber % 10;
			DigitIntoWord(lastDigit);
			System.out.print(" ");
			reverseNumber /=10;
		}
		
		for(int i=0;i< (getDigitCount(number)- (reverseCount)); i++) {
			DigitIntoWord(0);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public static int reverse(int number) {
        int reverseNumber = 0;

        while (number > 0) {
            int lastDigit = number % 10;
            reverseNumber = reverseNumber * 10 + lastDigit;
            number /= 10;
        }

        return reverseNumber;
    }
	
	public static int getDigitCount(int number) {
        if (number < 0) {
            return -1;
        }
        if (number == 0) {
            return 1;
        }

        int count = 0;
        while (number > 0) {
            count++;
            number /= 10;
        }
        return count;
    }

	public static void DigitIntoWord(int number){
        switch (number) {
            case 0:
                System.out.print("ZERO");
                break;
            case 1:
                System.out.print("ONE");
                break;
            case 2:
                System.out.print("TWO");
                break;
            case 3:
                System.out.print("THREE");
                break;
            case 4:
                System.out.print("FOUR");
                break;
            case 5:
                System.out.print("FIVE");
                break;
            case 6:
                System.out.print("SIX");
                break;
            case 7:
                System.out.print("SEVEN");
                break;
            case 8:
                System.out.print("EIGHT");
                break;
            case 9:
                System.out.print("NINE");
                break;
            default:
                System.out.print("OTHER");
                break;
        }
    }
	
}
