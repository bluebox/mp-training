import java.util.Scanner;

public class task16 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Is it summer? (true/false): ");
        while (!scanner.hasNextBoolean()) {
            System.out.println("Invalid input. Please enter 'true' or 'false'.");
            scanner.next(); // Consume the invalid input
        }
        boolean isSummer = scanner.nextBoolean();

        System.out.print("Enter the current temperature: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();
        }
        int temperature = scanner.nextInt();

        System.out.println("\nIs the cat playing? " + PlayingCat.isCatPlaying(isSummer, temperature));

        scanner.close();
}
}
public class PlayingCat {

    
    public static boolean isCatPlaying(boolean summer, int temperature) {
        int lowerLimit = 25;
        int upperLimit = 35;

        if (summer) {
            upperLimit = 45; // If it's summer, the upper limit for playing is 45
        }

        // Check if the temperature is within the allowed range
        return temperature >= lowerLimit && temperature <= upperLimit;
}
}