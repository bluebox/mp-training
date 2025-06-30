public class SharedDigit {

    public static boolean hasSharedDigit(int num1, int num2) {
        // Check if numbers are within the valid range (10-99 inclusive)
        if (num1 < 10 || num1 > 99 || num2 < 10 || num2 > 99) {
            return false;
        }

        // Extract digits of num1
        int num1FirstDigit = num1 / 10;
        int num1LastDigit = num1 % 10;

        // Extract digits of num2
        int num2FirstDigit = num2 / 10;
        int num2LastDigit = num2 % 10;

        // Check for shared digits
        if (num1FirstDigit == num2FirstDigit ||
            num1FirstDigit == num2LastDigit ||
            num1LastDigit == num2FirstDigit ||
            num1LastDigit == num2LastDigit) {
            return true;
        } else {
            return false;
        }
    }
}