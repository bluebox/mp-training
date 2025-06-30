public static int getEvenDigitSum(int number) {
    if (number < 0) {
        return -1; 
    }

    int sumOfEvenDigits = 0;
    int currentNumber = number;

    while (currentNumber > 0) {
        int digit = currentNumber % 10; 
        if (digit % 2 == 0) { 
            sumOfEvenDigits += digit; 
        }
        currentNumber /= 10; 
    }
    return sumOfEvenDigits;
}
