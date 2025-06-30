public class task18 {

    public static final String INVALID_VALUE_MESSAGE = "Invalid value";

 
    public static String getDurationString(int seconds) {
        if (seconds < 0) {
            return INVALID_VALUE_MESSAGE;
        }

        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        return getDurationString(minutes, remainingSeconds); // Call the overloaded method
    }

   
    public static String getDurationString(int minutes, int seconds) {
        if (minutes < 0 || seconds < 0 || seconds > 59) {
            return INVALID_VALUE_MESSAGE;
        }

        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;

        // Format to ensure two digits with leading zeros (e.g., 5 -> 05)
        String hoursString = String.format("%02dh", hours);
        String minutesString = String.format("%02dm", remainingMinutes);
        String secondsString = String.format("%02ds", remainingSeconds);

        return hoursString + " " + minutesString + " " + secondsString;
    }
}
import java.util.Scanner;

public class TestDurationConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Test getDurationString(int seconds) ---
        System.out.println("--- Convert Total Seconds ---");
        System.out.print("Enter total seconds: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer for seconds.");
            scanner.next();
        }
        int totalSeconds = scanner.nextInt();
        System.out.println("Result: " + DurationConverter.getDurationString(totalSeconds));

        // --- Test getDurationString(int minutes, int seconds) ---
        System.out.println("\n--- Convert Minutes and Seconds ---");
        System.out.print("Enter minutes: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer for minutes.");
            scanner.next();
        }
        int inputMinutes = scanner.nextInt();

        System.out.print("Enter seconds (0-59): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer for seconds.");
            scanner.next();
        }
        int inputSeconds = scanner.nextInt();

        System.out.println("Result: " + DurationConverter.getDurationString(inputMinutes, inputSeconds));

        scanner.close();
    }
}
