public class GreatestCommonDivisor {

    public static int getGreatestCommonDivisor(int first, int second) {
        // Check for invalid input parameters
        if (first < 10 || second < 10) {
            return -1; // Indicate invalid value
        }

        int gcd = 1; // Initialize GCD to 1
        int min = Math.min(first, second); // Find the minimum of the two numbers

        // Iterate from 1 up to the minimum of the two numbers
        for (int i = 1; i <= min; i++) {
            // Check if 'i' divides both numbers without a remainder
            if (first % i == 0 && second % i == 0) {
                gcd = i; // Update GCD if 'i' is a common divisor
            }
        }
        return gcd; // Return the greatest common divisor
    }
}