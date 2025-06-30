public class LargestPrimeFactor {

    public static int getLargestPrime(int number) {
        if (number <= 1) {
            return -1; // Invalid value for 0, 1, and negative numbers
        }

        int largestPrime = -1;

        // Divide by 2 until it's no longer divisible
        while (number % 2 == 0) {
            largestPrime = 2;
            number /= 2;
        }

        // Iterate for odd numbers starting from 3
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                largestPrime = i;
                number /= i;
            }
        }

        // If a prime number greater than 2 remains
        if (number > 2) {
            largestPrime = number;
        }

        return largestPrime;
    }
}