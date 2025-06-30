public static int getDigitCount(int number) {
    if (number < 0) {
        return -1;
    }
    if (number == 0) {
        return 1; // Special case for 0
    }
    int count = 0;
    while (number > 0) {
        number /= 10;
        count++;
    }
    return count;
}