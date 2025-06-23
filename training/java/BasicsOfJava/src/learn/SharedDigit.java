package learn;

public class SharedDigit {
	public static void main(String[] args) {
		
		int num1 = 12, num2 = 29;
		System.out.println(/*num1 +" " + num2 + " has shared digit " + */hasSharedDigit(num1,num2));
	}
	public static boolean hasSharedDigit(int num1, int num2)
	{
		if((num1 < 10) || (num2 < 10 || (num1 > 99) || (num2 > 99))) {
			return false;
		}
		int firstDigit1 = num1 / 10;
		int lastDigit1 = num1 % 10;
		int firstDigit2 = num2 / 10;
		int lastDigit2 = num2 % 10;
		
		return ((firstDigit1 == firstDigit2) || (lastDigit1 == lastDigit2)
				|| (firstDigit1 == lastDigit2) || (firstDigit2 == lastDigit1));
	}
}
