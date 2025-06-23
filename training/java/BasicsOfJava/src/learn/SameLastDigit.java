package learn;

public class SameLastDigit {
	public static void main(String[] args) {
		
		int num1 = 9,num2 = 99 ,num3 = 71;
		System.out.println(hasSameLastDigit(num1,num2,num3));
	}
	
	public static boolean hasSameLastDigit(int num1,int num2,int num3) {
		
		if(isValid(num1) && isValid(num2) && isValid(num3)) {
			
			int lastDigit1 = num1 % 10;
			int lastDigit2 = num2 % 10;
			int lastDigit3 = num3 % 10;
			
			if((lastDigit1 == lastDigit2) || (lastDigit2 == lastDigit3) || (lastDigit1 == lastDigit3)){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
	}
	
	public static boolean isValid(int number) {
		
		return number >= 10 && number<=1000;
	}
}
