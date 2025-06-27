package day_2_june26_basics_of_oops;

import java.util.Scanner;

public class InputCalculator {
	public static void main(String args[]) {
		inputThenPrintSumAndAverage();
	}
    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        int count = 0;

        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                sum += number;
                count++;
            } else {
                break; // Exit on non-integer input
            }
            scanner.nextLine(); // Move to next line (consume newline)
        }

        long average = (count == 0) ? 0 : Math.round((double) sum / count);
        System.out.println("SUM = " + sum + " AVG = " + average);

        scanner.close();
    }
}
