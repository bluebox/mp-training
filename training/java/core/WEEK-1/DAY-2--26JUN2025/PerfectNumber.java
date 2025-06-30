public class PerfectNumber {

    public static boolean isPerfectNumber(int number) {
        if (number < 1) {
            return false;
        }

        int sum = 0;

        // Find all proper divisors (less than number) that divide evenly
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        return sum == number;
    }

    public static void main(String[] args) {
        // Test cases based on the image
        System.out.println(isPerfectNumber(6));   // true  → 1 + 2 + 3 = 6
        System.out.println(isPerfectNumber(28));  // true  → 1 + 2 + 4 + 7 + 14 = 28
        System.out.println(isPerfectNumber(5));   // false → only divisor 1, 1 != 5
        System.out.println(isPerfectNumber(-1));  // false → number < 1
    }
}
