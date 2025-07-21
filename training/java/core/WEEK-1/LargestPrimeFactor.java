public class LargestPrimeFactor {

	public static void main(String[] args) {
        System.out.println(getLargestPrime(21));   // Output: 7
        System.out.println(getLargestPrime(217));  // Output: 31
        System.out.println(getLargestPrime(0));    // Output: -1
        System.out.println(getLargestPrime(45));   // Output: 5
        System.out.println(getLargestPrime(-1));   // Output: -1
    }

    public static int getLargestPrime(int number) {
        if (number < 2) {
            return -1;
        }

        int largestPrime = -1;
        for (int i = 2; i <= number; i++) {
            while (number % i == 0) {
                largestPrime = i;
                number /= i;
            }
        }

        return largestPrime;
    }
}