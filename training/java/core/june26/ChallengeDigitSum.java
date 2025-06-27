package com.tulasidhar.june26;

public class ChallengeDigitSum {

    public static void main(String[] args) {
        System.out.println(sumDigits(125));   // Output: 8 (1+2+5)
        System.out.println(sumDigits(1000));  // Output: 1 (1+0+0+0)
        System.out.println(sumDigits(9));     // Output: 9 (single digit)
        System.out.println(sumDigits(-10));   // Output: -1 (invalid input)
    }

    public static int sumDigits(int number) {
        if (number < 0) {
            return -1;  // Invalid input
        }

        int sum = 0;
        while (number > 0) {
            sum += number % 10;  // Add the last digit
            number = number / 10; // Remove the last digit
        }

        return sum;
    }
}
