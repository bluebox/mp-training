
public class LastDigitForMultipleIntegerComparison {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(hasSameLastDigit(41,22,71) ){
		System.out.println("has Same Last Digit");
		}
		else {
		System.out.println("has no Same Last Digit");
		}
		
		if(hasSameLastDigit(23,32,42) ){
			System.out.println("has Same Last Digit");
			}
		else {
			System.out.println("has no Same Last Digit");
			}
		
		if(hasSameLastDigit(9,99,999) ){
			System.out.println("has Same Last Digit");
			}
		else {
			System.out.println("has no Same Last Digit");
			}
		
		if(isValid(10)) {
		System.out.println("Valid number between 10 - 1000");
		}
		else {
		System.out.println("Not a Valid number between 10 - 1000");
		}
		
		if(isValid(468)) {
			System.out.println("Valid number between 10 - 1000");
			}
		else {
			System.out.println("Not a Valid number between 10 - 1000");
			}
		
		if(isValid(1051)) {
			System.out.println("Valid number between 10 - 1000");
			}
		else {
			System.out.println("Not a Valid number between 10 - 1000");
			}


	}
	
	
	public static boolean hasSameLastDigit(int a, int b, int c) {
	    if (a < 10 || b < 10 || c < 10 || a > 1000 || b > 1000 || c > 1000) {
	        return false;
	    } else {
	        int lastDigitA = a % 10;
	        int lastDigitB = b % 10;
	        int lastDigitC = c % 10;

	        return (lastDigitA == lastDigitB || 
	                lastDigitA == lastDigitC || 
	                lastDigitB == lastDigitC);
	    }
	}

	public static boolean isValid(int n) {
		if(n>=10 && n<=1000) {
			return true;
		}
		else {
			return false;
		}
	}
}
