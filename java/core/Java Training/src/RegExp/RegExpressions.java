package RegExp;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegExpressions {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = userInput(scanner, "Enter your name:", "^[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*$");
        String email = userInput(scanner, "Enter your email:", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        String mobile = userInput(scanner, "Enter your mobile number (10 digits, starts with 6-9):", "^[6-9]\\d{9}$");
        String dob = userInput(scanner, "Enter your DOB (DD-MM-YYYY):", "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[0-2])[- /.](19|20)\\d{2}$");
        String address = userInput(scanner, "Enter your address:", "^[\\w\\s#.,'-]{5,100}$");

        System.out.println("\n All inputs are valid:");
        System.out.println("Name    : " + name);
        System.out.println("Email   : " + email);
        System.out.println("Mobile  : " + mobile);
        System.out.println("DOB     : " + dob);
        System.out.println("Address : " + address);

        scanner.close();
    }

    public static String userInput(Scanner scanner, String prompt, String regex) {
        String input;
        Pattern pattern = Pattern.compile(regex);

        while (true) {
            System.out.print(prompt + " ");
            input = scanner.nextLine().trim();

            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                break;
            } 
            else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }
}
