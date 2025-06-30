import java.util.Scanner;

public class task27 {

    public static void InputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int count = 0;

        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                sum += number;
                count++;
            } else {
                break; // User entered something that is not an int
            }
            scanner.nextLine(); // Handle end of line (enter key)
        }

        long average = 0;
        if (count > 0) {
            average = Math.round((double) sum / count); // Calculate average as long
        }

        System.out.println("SUM=" + sum + " AVG=" + average);
        scanner.close();
    }
}