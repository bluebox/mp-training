package learn;

public class LargestPrimeFactor {
	public static void main(String[] args) {
		
		int number = -23;
		System.out.println(getLargestPrimeFactor(number));
	}
	
	public static int getLargestPrimeFactor(int number){
		
//		int maxPrime = 0;
		for(int i = number;i >= 2; i--) {
			
			if(number % i == 0) {
				if(isPrime(i)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public static boolean isPrime(int number) {
		
		for(int i = 2; i <= number / 2 ;i++) {
			if(number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
