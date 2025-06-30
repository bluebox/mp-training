import java.util.InputMismatchException;
import java.util.Scanner;

public class ReadingUserInputChallenge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int count = 0;

        while (count < 5) {
            System.out.print("Enter number #" + (count + 1) + ": ");
            try {
                int number = scanner.nextInt();
                sum += number;
                count++;
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }

        System.out.println("The sum of the 5 valid numbers is: " + sum);
        scanner.close();
    }
}