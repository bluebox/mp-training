package June26;

public class SharedDigit {
	public static void main(String[] args) {
		System.out.println(hasSharedDigit(12, 23)); 
        System.out.println(hasSharedDigit(9, 99)); 
        System.out.println(hasSharedDigit(15, 55)); 
	}
	public static boolean hasSharedDigit(int num1, int num2) {
		if (num1 < 10 || num1 > 99 || num2 < 10 || num2 > 99)  return false; 
        int firstNum1 = num1 / 10; 
        int secondNum1 = num1 % 10; 
        int firstNum2 = num2 / 10; 
        int secondNum2 = num2 % 10; 
        return (firstNum1 == firstNum2 || firstNum1 == secondNum2 || secondNum1 == firstNum2 || secondNum1 == secondNum2);
 	}
}
