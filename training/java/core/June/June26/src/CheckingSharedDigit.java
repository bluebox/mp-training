
public class CheckingSharedDigit {
	public static void main(String[] args) {
		
		System.out.println(hasSharedDigit(12,23));
		System.out.println(hasSharedDigit(9,99));
		System.out.println(hasSharedDigit(15, 55));
	}
	
	public static boolean hasSharedDigit(int number1, int number2) {
		
		if (number1 < 10 || number1 > 99 || number2 < 10 || number2 > 99) {
			return false;
		}
		
		int number1FirstDigit = number1 /10;
		int number1SecondDigit = number1 %10;
		
		int number2FirstDigit = number2 /10;
		int number2SecondDigit = number2 %10;
		
		return ( number1FirstDigit  == number2FirstDigit  || 
				 number1FirstDigit  == number2SecondDigit ||
				 number1SecondDigit == number2FirstDigit  || 
				 number1SecondDigit == number2SecondDigit );
		
	}
}
