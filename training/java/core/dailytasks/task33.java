public static int getEvenDigitSum(int number) {
    if (number < 0) {
        return -1; // Return -1 for negative numbers as per instructions
    }

    int sumOfEvenDigits = 0;
    int currentNumber = number;

    while (currentNumber > 0) {
        int digit = currentNumber % 10; // Get the last digit
        if (digit % 2 == 0) { // Check if the digit is even
            sumOfEvenDigits += digit; // Add even digit to the sum
        }
        currentNumber /= 10; // Remove the last digit
    }
    return sumOfEvenDigits;
}