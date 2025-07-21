public class DigitSumChallenge {
    public static int sumDigits(int number) {
        if (number < 0) {
            return -1; // Invalid input
        }

        int sum = 0;

        while (number > 0) {
            int digit = number % 10;  // Extract last digit
            sum += digit;             // Add digit to sum
            number = number / 10;     // Remove last digit
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Sum of digits (125): " + sumDigits(125));     // Output: 8
        System.out.println("Sum of digits (1000): " + sumDigits(1000));   // Output: 1
        System.out.println("Sum of digits (-12): " + sumDigits(-12));     // Output: -1 (invalid)
        System.out.println("Sum of digits (7): " + sumDigits(7));         // Output: 7
    }    
}
