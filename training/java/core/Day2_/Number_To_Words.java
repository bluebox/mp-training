package com.Day2_;

public class Number_To_Words {
    public static void main(String[] args) {
        numberToWords(99495);
        numberToWords(92611);
    }

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
            printDigitAsWord(digit);
            reversed /= 10;
        }

        // Handle trailing zeros after reversing (e.g., 100 -> "One Zero Zero")
        for (int i = 0; i < originalDigitCount - reversedDigitCount; i++) {
            printDigitAsWord(0);
        }

        // Special case: number is 0
        if (number == 0) {
            printDigitAsWord(0);
        }
    }

    public static void printDigitAsWord(int digit) {
        switch (digit) {
            case 0:
                System.out.print("Zero");
                break;
            case 1:
                System.out.print("One");
                break;
            case 2:
                System.out.print("Two");
                break;
            case 3:
                System.out.print("Three");
                break;
            case 4:
                System.out.print("Four");
                break;
            case 5:
                System.out.print("Five");
                break;
            case 6:
                System.out.print("Six");
                break;
            case 7:
                System.out.print("Seven");
                break;
            case 8:
                System.out.print("Eight");
                break;
            case 9:
                System.out.print("Nine");
                break;
        }
    }

    public static int reverse(int number) {
        int reversed = 0;
        int sign = number < 0 ? -1 : 1;
        number = Math.abs(number);

        while (number > 0) {
            reversed = reversed * 10 + number % 10;
            number /= 10;
        }

        return reversed * sign;
    }

    public static int getDigitCount(int number) {
        if (number < 0) return -1;
        if (number == 0) return 1;

        int count = 0;
        while (number > 0) {
            count++;
            number /= 10;
        }

        return count;
    }
}
