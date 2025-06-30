package day2;

import java.util.Scanner;

public class MinAndMaxInput{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        boolean hasEnteredNumber = false;

        while (true) {
            System.out.println("Enter a number, or any non-number to quit:");

            if (scanner.hasNextDouble()) {
                double number = scanner.nextDouble();
                if (!hasEnteredNumber) {
                    min = max = number;
                    hasEnteredNumber = true;
                } else {
                    if (number < min) min = number;
                    if (number > max) max = number;
                }
            } else {
                break; // Non-numeric input encountered
            }

            scanner.nextLine(); // Clear the buffer
        }

        if (hasEnteredNumber) {
            System.out.println("Minimum number entered: " + min);
            System.out.println("Maximum number entered: " + max);
        } else {
            System.out.println("No valid numbers were entered.");
        }

        scanner.close();
    }
}