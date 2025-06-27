package Day2_26_06;

import java.util.Scanner;

public class MinMaxInput {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double min = 0;
        double max = 0;
        boolean firstInput = true;

        while (true) {
            System.out.print("Enter a number (or any letter to quit): ");

            if (scanner.hasNextDouble()) {
                double number = scanner.nextDouble();

                if (firstInput) {
                    min = number;
                    max = number;
                    firstInput = false;
                } else {
                    if (number < min) {
                        min = number;
                    }
                    if (number > max) {
                        max = number;
                    }
                }
            } else {
                break;
            }
        }

        if (!firstInput) {
            System.out.println("Minimum number entered: " + min);
            System.out.println("Maximum number entered: " + max);
        } else {
            System.out.println("No valid numbers were entered.");
        }

        scanner.close();
    }
}
