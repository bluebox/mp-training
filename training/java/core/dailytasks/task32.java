public class FirstLastDigitSum {

    public static int sumFirstAndLastDigit(int number) {
        if (number < 0) {
            return -1; // Return -1 for negative numbers
        }

        int lastDigit = number % 10; // Get the last digit

        int firstDigit = number;
        while (firstDigit >= 10) {
            firstDigit /= 10; // Remove the last digit until only the first remains
        }

        return firstDigit + lastDigit; // Return the sum of the first and last digits
    }
}