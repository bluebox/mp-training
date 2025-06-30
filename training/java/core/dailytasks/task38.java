public static boolean isPerfectNumber(int number) {
    // Check if the number is less than 1
    if (number < 1) {
        return false;
    }

    // Calculate the sum of proper divisors
    int sumOfDivisors = 0;
    for (int i = 1; i < number; i++) {
        if (number % i == 0) { // Check if 'i' is a divisor of 'number'
            sumOfDivisors += i;
        }
    }

    // Compare the sum of divisors with the number
    return sumOfDivisors == number;
}