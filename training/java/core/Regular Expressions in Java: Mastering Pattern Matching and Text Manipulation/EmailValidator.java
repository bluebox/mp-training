import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class EmailValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Email regex pattern based on rules
        String regex = "^([a-zA-Z0-9._-]+)@([a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";

        Pattern pattern = Pattern.compile(regex);

        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            System.out.println("Valid Email ");
            System.out.println("Username: " + matcher.group(1));
            System.out.println("Domain  : " + matcher.group(2));
        } else {
            System.out.println("Invalid Email ");
        }

        scanner.close();
    }
}
