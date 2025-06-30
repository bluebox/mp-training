public class NumberToWords {

    // Main method to test
    public static void main(String[] args) {
        numberToWords(123);     // One Two Three
        numberToWords(100);     // One Zero Zero
        numberToWords(1010);    // One Zero One Zero
        numberToWords(-12);     // Invalid Value
        numberToWords(0);       // Zero
    }

    // Method 1: Convert digits to words
    public static void numberToWords(int number) {
        if (number < 0) {
            System.out.println("Invalid Value");
            return;
        }

        int reversed = reverse(number);
        int originalDigitCount = getDigitCount(number);
        int reversedDigitCount = getDigitCount(reversed);

        while (reversed > 0) {
            int digit = reversed % 10;
            printDigitInWords(digit);
            reversed /= 10;
        }

        // To handle trailing zeros
        for (int i = 0; i < (originalDigitCount - reversedDigitCount); i++) {
            printDigitInWords(0);
        }

        // Special case for input 0
        if (number == 0) {
            printDigitInWords(0);
        }
    }

    // Helper method: Print a digit in words
    private static void printDigitInWords(int digit) {
        switch (digit) {
            case 0 :System.out.println("Zero");
            case 1 :System.out.println("One");
            case 2 :System.out.println("Two");
            case 3 :System.out.println("Three");
            case 4 :System.out.println("Four");
            case 5 :System.out.println("Five");
            case 6 :System.out.println("Six");
            case 7 :System.out.println("Seven");
            case 8 :System.out.println("Eight");
            case 9 :System.out.println("Nine");
        }
    }

    // Method 2: Reverse the number
    public static int reverse(int number) {
        int reversed = 0;
        int sign = number < 0 ? -1 : 1;
        number = Math.abs(number);

        while (number > 0) {
            int lastDigit = number % 10;
            reversed = reversed * 10 + lastDigit;
            number /= 10;
        }

        return reversed * sign;
    }

    // Method 3: Get digit count
    public static int getDigitCount(int number) {
        if (number < 0) return -1;
        if (number == 0) return 1;

        int count = 0;
        while (number > 0) {
            number /= 10;
            count++;
        }
        return count;
    }
}
