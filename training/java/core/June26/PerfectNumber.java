package June26;

public class PerfectNumber {
	public static void main(String[] args) {
		System.out.println(isPerfectNumber(6));
		System.out.println(isPerfectNumber(28));
		System.out.println(isPerfectNumber(5));
		System.out.println(isPerfectNumber(-1));
	}
	public static boolean isPerfectNumber(int number) {
		if (number < 0) return false;
		int sum = sumOfDivisors(number);
		return sum==number;
	}
	public static int sumOfDivisors(int number) {
		int sum = 0;
		for(int i = 1; i < number; i++) {
			if(number % i == 0) sum += i;
		}
		return sum;
	}
}
