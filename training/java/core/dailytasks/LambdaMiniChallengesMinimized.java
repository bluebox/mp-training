import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.function.Supplier;

public class LambdaMiniChallengesMinimized {

    // Mc 2: Static method everySecondCharStatic
    public static String everySecondCharStatic(String source) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {

            if ((i + 1) % 2 == 0) {
                result.append(source.charAt(i));
            }
        }
        return result.toString();
    }

    // Mc 4: Method to accept a functional interface
    public static String executeFunctionalMethod(UnaryOperator<String> func, String inputString) {
        return func.apply(inputString);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Lambda Mini Challenges (Minimized) ---");

        //  Mc 1: Consumer as a Lambda
        System.out.println("\n--- MC1: Consumer Lambda ---");
        System.out.print("Enter sentence to split: ");
        String sentence = scanner.nextLine();
        Consumer<String> printParts = s -> {
            System.out.println("Parts:");
            for (String part : s.split(" ")) {
                System.out.println(part);
            }
        };
        printParts.accept(sentence);

        // Mc2: Using the static method 
        System.out.println("\n--- MC2: Static Method ---");
        System.out.print("Enter string for everySecondChar: ");
        String mc2Input = scanner.nextLine();
        System.out.println("Result: " + everySecondCharStatic(mc2Input));

        // Mc 3: UnaryOperator Lambda 
        System.out.println("\n--- MC3: UnaryOperator Lambda ---");
        UnaryOperator<String> everySecondCharLambda = source -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if ((i + 1) % 2 == 0) { 
                    result.append(source.charAt(i));
                }
            }
            return result.toString();
        };
        String mc3TestString = "1234567890";
        System.out.println("Lambda result for \"" + mc3TestString + "\": " + everySecondCharLambda.apply(mc3TestString));

        // Mc 4 & 5: Pass Lambda to Method
        System.out.println("\n--- MC4 & 5: Pass Lambda to Method ---");
        String mc45Input = "1234567890";
        String mc45Result = executeFunctionalMethod(everySecondCharLambda, mc45Input);
        System.out.println("Method result with lambda for \"" + mc45Input + "\": " + mc45Result);

        //  Mc 6 & 7: Supplier Lambda
        System.out.println("\n--- MC6 & 7: Supplier Lambda ---");
        Supplier<String> iLoveJava = () -> "I love Java!";
        String supplierOutput = iLoveJava.get();
        System.out.println("Supplier output: " + supplierOutput);

        scanner.close();
        System.out.println("\n--- All Challenges Done ---");
    }
}