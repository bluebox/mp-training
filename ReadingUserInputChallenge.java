import java.util.Scanner;

public class ReadingUserInputChallenge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 1;
        int sum = 0;

        while (count <= 5) {
            System.out.print("Enter number #" + count + ": ");

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                sum += number;
                count++;
            } else {
                System.out.println("Invalid number");
            }

            scanner.nextLine(); 
        }

        System.out.println("Sum of the 5 valid numbers: " + sum);
        scanner.close();
    }
}