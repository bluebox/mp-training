package dayjunfifth;

import java.util.regex.*;

public class RegexExample {

    public static void main(String[] args) {
        // 1. Pattern Matching
        String input = "The price 67 is 123 dol9057lars";
        String regex = "\\d+"; // matches one or more digits

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        System.out.println("=== Pattern Matching Example ===");
        if (matcher.find()) {
            System.out.println("First match found: " + matcher.group());
        } else {
            System.out.println("No match found.");
        }

        // 2. Validating Email
        String email = "988755899test@example.com";
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);

        System.out.println("\n=== Email Validation ===");
        if (emailMatcher.matches()) {
            System.out.println("Valid email address.");
        } else {
            System.out.println("Invalid email address.");
        }

        // 3. Finding All Words Starting with Capital Letter
        String text = "Java is Developed by James Gosling at Sun Microsystems.";
        String capitalWordRegex = "[A-Z][a-zA-Z]*";

        Pattern capPattern = Pattern.compile(capitalWordRegex);
        Matcher capMatcher = capPattern.matcher(text);

        System.out.println("\n=== Capitalized Words in Sentence ===");
        while (capMatcher.find()) {
            System.out.println("Found: " + capMatcher.group());
        }
    }
}

