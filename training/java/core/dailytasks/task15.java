import java.util.Scanner;

public class TestTimeConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of minutes: ");
        while (!scanner.hasNextLong()) {
            System.out.println("Invalid input. Please enter a whole number for minutes.");
            scanner.next(); // Consume the invalid input
        }
        long minutes = scanner.nextLong();

        System.out.print("Result: ");
        TimeConverter.printYearsAndDays(minutes); // Call the method from the other class

        scanner.close();
}
}
public class task15 {

    public static void printYearsAndDays(long minutes) {
        if (minutes < 0) {
            System.out.println("Invalid Value");
            return;
        }

        long hours = minutes / 60;
        long days = hours / 24;
        long years = days / 365;
        long remainingDays = days % 365; // Days remaining after accounting for full years

        // Format: "XX min = YY y and ZZ d"
        System.out.println(minutes + " min = " + years + " y and " + remainingDays +"d");
}
}



