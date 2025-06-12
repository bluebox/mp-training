package Main;

import java.util.Scanner;

public class MinAndMax {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = 0;
        int max = 0;
        boolean firstInput = true;
        while (true) {
            System.out.println("Enter a number (or any non-number to quit):");

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();

                if (firstInput) {
                    min = number;
                    max = number;
                    firstInput = false;
                } 
                else {
                    if (number < min) {
                        min = number;
                    }
                    if (number > max) {
                        max = number;
                    }
                }
            } 
            else {
                break;
            }

            scanner.nextLine();
        }

        if (firstInput) {
            System.out.println("No valid numbers entered.");
        } 
        else {
            System.out.println("Minimum number entered: " + min);
            System.out.println("Maximum number entered: " + max);
        }
        scanner.close();
    }
}
