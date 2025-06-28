package June26;

public class EvaluateLastDigit {
	public static void main(String[] args) {
		System.out.println(hasSameLastDigit(41, 22, 71));
		System.out.println(hasSameLastDigit(23, 32, 42));
		System.out.println(hasSameLastDigit(9, 99, 999));
	}
	public static boolean hasSameLastDigit (int num1, int num2, int num3) {
		if (isValid(num1) && isValid(num2) && isValid(num3)) {
			int lstNum1 = num1 % 10; 
			int lstNum2 = num2 % 10; 
			int lstNum3 = num3 % 10;
			return (lstNum1 == lstNum2 || lstNum1 == lstNum3 || lstNum3 == lstNum2);
		}
		return false; 
	}
	public static boolean isValid(int number) {
		if(number < 10 || number > 1000) return false;
		return true;
	}
}
