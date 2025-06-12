package Main;

public class Assignment {
	public static void main(String args[]) {
		int firstNumber = 6;
		
		System.out.println("Examples for assignment operator using arithmetic operators");
		System.out.println("firstNumber += 3 is equal to "+(firstNumber += 3));
		System.out.println("firstNumber -= 2 is equal to "+(firstNumber -= 2));
		System.out.println("firstNumber *= 5 is equal to "+(firstNumber *= 5));
		System.out.println("firstNumber /= 4 is equal to "+(firstNumber /= 4));
		System.out.println("firstNumber %= 3 is equal to "+(firstNumber %= 3));
		
		int bitNumber=5;
		System.out.println("Examples for assignment operator using bitwise operators");
		System.out.println("bitNumber |= 3 is equal to "+(bitNumber |= 3));
		System.out.println("bitNumber &= 2 is equal to "+(bitNumber &= 2));
		System.out.println("bitNumber ^= 5 is equal to "+(bitNumber ^= 7));
		
		int shiftNumber=7;
		System.out.println("Examples for assignment operator using shift operators");
		System.out.println("shiftNumber <<= 3 is equal to "+(shiftNumber <<= 3));
		System.out.println("shiftNumber >>= 2 is equal to "+(shiftNumber >>= 2));
		System.out.println("shiftNumber >>>= 1 is equal to "+(shiftNumber >>>= 5));
	}
}
