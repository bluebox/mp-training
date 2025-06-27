package com.tulasidhar.june26;

public class ChallengeSumOddNumsInRange {

    public static void main(String[] args) {
        System.out.println(sumOdd(1, 100));
        System.out.println(sumOdd(-1, 100));
        System.out.println(sumOdd(100, 100));
        System.out.println(sumOdd(13, 13));
        System.out.println(sumOdd(100, -100));
        System.out.println(sumOdd(100, 1000));

        // Run Step 2 While Loop Challenge
        runWhileLoopChallenge();
    }

    // Check if number is odd
    public static boolean isOdd(int number) {
        if (number <= 0) {
            return false;
        }
        return number % 2 != 0;
    }

    // Check if number is even
    public static boolean isEvenNumber(int number) {
        return number % 2 == 0;
    }

    // Sum all odd numbers in range
    public static int sumOdd(int start, int end) {
        if (start < 0 || end < start) {
            return -1;
        }

        int sum = 0;
        for (int i = start; i <= end; i++) {
            if (isOdd(i)) {
                sum += i;
            }
        }
        return sum;
    }

    // While Loop Challenge Step 2
    public static void runWhileLoopChallenge() {
        int number = 5;
        int endNumber = 20;
        int evenCount = 0;
        int oddCount = 0;

        while (number <= endNumber) {
            if (isEvenNumber(number)) {
                System.out.println(number + " is even.");
                evenCount++;
                if (evenCount == 5) {
                    break; // Stop after finding 5 even numbers
                }
            } else {
                oddCount++;
            }
            number++;
        }

        System.out.println("Total even numbers found: " + evenCount);
        System.out.println("Total odd numbers found: " + oddCount);
    }
}
