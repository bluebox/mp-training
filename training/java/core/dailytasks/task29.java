import java.util.InputMismatchException;
import java.util.Scanner;

public class task29 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double minNumber = Double.MAX_VALUE;
        double maxNumber = Double.MIN_VALUE;
        boolean firstNumberEntered = false;

        System.out.println("Enter numbers to track min/max. Enter any character to quit.");

        while (true) {
            System.out.print("Enter a number: ");
            String input = scanner.nextLine();

            try {
                
                double currentNumber = Double.parseDouble(input);

               
                if (!firstNumberEntered) {
                    minNumber = currentNumber;
                    maxNumber = currentNumber;
                    firstNumberEntered = true;
                } else {
                    if (currentNumber < minNumber) {
                        minNumber = currentNumber;
                    }
                    if (currentNumber > maxNumber) {
                        maxNumber = currentNumber;
                    }
                }
            } catch (NumberFormatException e) {
               
                System.out.println("Invalid input. Exiting loop.");
                break;
            }
        }

        if (firstNumberEntered) {
            System.out.println("Minimum number entered: " + minNumber);
            System.out.println("Maximum number entered: " + maxNumber);
        } else {
            System.out.println("No numbers were entered.");
        }

        scanner.close();
    }
}
