
public class EvaluatingLastDigit {
	public static void main(String[] args) {
		
		System.out.println(isValid(10));
		System.out.println(isValid(468));
		System.out.println(isValid(1051));
		
		System.out.println(hasSameLastDigit(41, 22, 71));
		System.out.println(hasSameLastDigit(23, 32, 42));
        System.out.println(hasSameLastDigit(9, 99, 999));
		
	}
	
	public static boolean hasSameLastDigit(int number1, int number2, int number3) {
		if (!isValid(number1) || !isValid(number2) || !isValid(number3)) {
			return false;
		}
		int lastDigit1 = number1 % 10;
		int lastDigit2 = number2 % 10;
		int lastDigit3 = number3 % 10;
		
		return (
				lastDigit1 == lastDigit2 ||
				lastDigit1 == lastDigit3 ||
				lastDigit2 == lastDigit3
				);
				
		
	}
	
	public static boolean isValid(int num) {
		
		if(num < 10 || num > 1000) {
			return false;
		}
		
		return true;
	}
}
