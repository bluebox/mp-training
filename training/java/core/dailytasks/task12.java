import java.util.Scanner;

public class task12 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer to get its word representation: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // Consume the invalid input
        }
        int number = scanner.nextInt();

        System.out.print("The word representation is: ");
        task12.task12(number); // Call the method from the other class

        scanner.close();
}
}