import java.util.Scanner;

public class InteractiveCalculator {

    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int count = 0;
        long average = 0; // Use long for average as per the problem description

        while (true) {
            try {
                // Prompt user for input (optional, but good practice)
                // System.out.print("Enter an integer (or non-integer to finish): ");
                int number = scanner.nextInt();
                sum += number;
                count++;
            } catch (java.util.InputMismatchException e) {
                // User entered non-integer, break the loop
                System.out.println("SUM = " + sum + " AVG = " + average);
                break;
            } finally {
                // Clear the scanner buffer if a non-integer was entered
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
            // Calculate average after each input to handle the case where the first input is non-integer
            if (count > 0) {
                average = Math.round((double) sum / count); // Calculate rounded average
            } else {
                average = 0; // If no numbers entered, average is 0
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }
}