package June26;

//Getting largest prime factor

public class LargestPrime {
	public static void main(String[] args) {
		System.out.println(getLargestPrime(21));
		System.out.println(getLargestPrime(217));
		System.out.println(getLargestPrime(0));
		System.out.println(getLargestPrime(45));
		System.out.println(getLargestPrime(-1));
	}
	public static int getLargestPrime(int number) {
		if(number <= 1) return -1;
		for(int i = number-1; i > 1; i--) {
			if(number % i == 0) {
				boolean flag = true;
				for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                    	flag = false;
                    	continue;
                    }
				}
				if(flag) return i;
			}
		}
		return -1;
	}
}
