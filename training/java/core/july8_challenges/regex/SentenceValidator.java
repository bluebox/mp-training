package Regex;



import java.util.Scanner;

public class SentenceValidator {

    private static final String CHALLENGE_1_PATTERN = "Hello, World!";
    private static final String CHALLENGE_2_PATTERN = "^[A-Z][a-z]*(?: [a-z]+)*\\.$";
    private static final String CHALLENGE_3_PATTERN = "^[A-Z][\\w\\s,:'\\.\\-]*[.!?]$";

    public void runValidation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Challenge 1: Enter a sentence to match exactly 'Hello, World!'");
        String input1 = scanner.nextLine();
        System.out.println("Matches: " + input1.matches(CHALLENGE_1_PATTERN));

        System.out.println("\nChallenge 2: Enter a sentence starting with a capital letter and ending with a period (lowercase only)");
        String input2 = scanner.nextLine();
        System.out.println("Matches: " + input2.matches(CHALLENGE_2_PATTERN));

        System.out.println("\nChallenge 3: Enter a sentence starting with capital letter, allowing punctuation, ending with '.', '!' or '?'");
        String input3 = scanner.nextLine();
        System.out.println("Matches: " + input3.matches(CHALLENGE_3_PATTERN));

        scanner.close();
    }
}
