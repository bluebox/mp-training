import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class methodandlamda {

    public static void transformNames(String[] names, List<UnaryOperator<String>> transformations) {
        for (int i = 0; i < names.length; i++) {
            String currentName = names[i];

            for (UnaryOperator<String> transform : transformations) {
                currentName = transform.apply(currentName);
            }
            names[i] = currentName;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("--- Method and Lambda Challenge ---");

        System.out.print("Enter names separated by commas (e.g., 'john doe,anna smith,peter pan'): ");
        String namesInput = scanner.nextLine();
        String[] names = Arrays.stream(namesInput.split(","))
                               .map(String::trim)
                               .toArray(String[]::new);

        System.out.println("\nOriginal Names: " + Arrays.toString(names));

        List<UnaryOperator<String>> transformations = new ArrayList<>();

        transformations.add(String::toUpperCase);
        System.out.println("\nAdding transformation: Make all names UPPERCASE");

        transformations.add(name -> {
            char middleInitial = (char) ('A' + random.nextInt(26));
            String[] parts = name.split(" ");
            if (parts.length > 1) {
                return parts[0] + " " + middleInitial + ". " + String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
            } else {
                return name + " " + middleInitial + ".";
            }
        });
        System.out.println("Adding transformation: Add a random middle initial");

        transformations.add(name -> {
            String[] parts = name.split(" ");
            String firstName = parts[0];
            String reversedFirstName = new StringBuilder(firstName).reverse().toString();

            return name + " " + reversedFirstName;
        });
        System.out.println("Adding transformation: Add a last name (reverse of first)");

        transformations.add(name -> {
            if (name.toLowerCase().contains("anna") || name.toLowerCase().contains("alice")) {
                return "Ms. " + name;
            } else {
                return "Mr. " + name;
            }
        });
        System.out.println("Adding transformation: Prepend Mr./Ms.");

        transformations.add(name -> name.replace("A", "@").replace("a", "@"));
        System.out.println("Adding transformation: Replace 'A'/'a' with '@'");

        transformNames(names, transformations);

        System.out.println("\nTransformed Names: " + Arrays.toString(names));

        scanner.close();
    }
}