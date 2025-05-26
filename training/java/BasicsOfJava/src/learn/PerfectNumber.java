package learn;

public class PerfectNumber {
	public static void main(String[] args) {
		
		int number = 5;
		System.out.println(isPerfectNumber(number));
	}
	
	public static boolean isPerfectNumber(int number) {
		
		int sum = 0;
		for(int i = 1; i <= number/2; i++) {
			if(number % i == 0 ) {
				sum += i;
			}
		}
		
		return sum == number;
	}
}
