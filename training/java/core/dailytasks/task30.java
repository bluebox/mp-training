public class task30 {

    public static int sumFirstAndLastDigit(int number) {
        if (number < 0) {
            return -1; // Handle negative numbers
        }

        int lastDigit = number % 10; // Get the last digit

        int firstDigit = number;
        while (firstDigit >= 10) {
            firstDigit /= 10; // Remove the last digit until only the first remains
        }

        return firstDigit + lastDigit; // Return the sum of first and last digits
    }

    public static void main(String[] args) {
        System.out.println("Sum of first and last digit of 252: " + sumFirstAndLastDigit(252)); // Expected: 4
        System.out.println("Sum of first and last digit of 257: " + sumFirstAndLastDigit(257)); // Expected: 9
        System.out.println("Sum of first and last digit of 0: " + sumFirstAndLastDigit(0));   // Expected: 0
        System.out.println("Sum of first and last digit of 5: " + sumFirstAndLastDigit(5));   // Expected: 10
        System.out.println("Sum of first and last digit of -10: " + sumFirstAndLastDigit(-10)); // Expected: -1
    }
}