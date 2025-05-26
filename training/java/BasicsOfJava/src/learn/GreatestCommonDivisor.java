package learn;

public class GreatestCommonDivisor {
	
	public static void main(String[] args) {
		
		int num1 = 12;
		int num2 = 30;
		System.out.println("GCD of "+ num1 + ", " + num2 + " is " + getGreatestCommonDivisor(num1,num2));
		
	}
	
	public static int getGreatestCommonDivisor(int num1, int num2) {
		
		if((num1 < 10) || (num2 < 10)) {
			return -1;
		}
		
		while(num1 % num2 != 0) {
			
			int temp = num2;
			num2 = num1 % num2;
			num1 = temp;
		}
		
		return num2;
	}
}
