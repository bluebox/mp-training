import java.util.Scanner;

public class MinMaxChallenge {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double minNumber = Double.MAX_VALUE;
        double maxNumber = Double.MIN_VALUE;
        boolean firstInput = true;

        while (true) {
            System.out.print("Enter number (or any character to quit): ");
            String input = scanner.nextLine();

            try {
                double number = Double.parseDouble(input);

                if (firstInput) {
                    minNumber = number;
                    maxNumber = number;
                    firstInput = false;
                } else {
                    if (number < minNumber) {
                        minNumber = number;
                    }
                    if (number > maxNumber) {
                        maxNumber = number;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Quitting program.");
                break;
            }
        }

        if (!firstInput) { 
            System.out.println("Minimum number entered: " + minNumber);
            System.out.println("Maximum number entered: " + maxNumber);
        } else {
            System.out.println("No numbers were entered.");
        }

        scanner.close();
    }
}
