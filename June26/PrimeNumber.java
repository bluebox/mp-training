package June26;

public class PrimeNumber {
	public static void main(String[] args) {
		int counter=0;
		System.out.println("First 3 Prime Numbers are");
		for(int i = 0; i <= 1000 && counter < 3; i++) {
			if(isPrime(i)) {
				System.out.println(i);
				counter++;
			}
			//if(counter == 3) break;
		}
	}
	public static boolean isPrime (int number) {
		if(number <= 2) return (number==2);
		for(int divisor = 2; divisor <= number/2; divisor++) {
			if(number % divisor == 0) return false;
		}
		return true;
	}
}
