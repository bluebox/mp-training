public static int getDigitCount(int number) {
    if (number < 0) {
        return -1; // Invalid value
    }
    if (number == 0) {
        return 1; // Zero has one digit
    }
    int count = 0;
    while (number > 0) {
        number /= 10;
        count++;
    }
    return count;
}