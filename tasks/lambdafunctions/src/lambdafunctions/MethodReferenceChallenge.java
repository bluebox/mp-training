package lambdafunctions;

import java.util.*;
import java.util.function.*;

public class MethodReferenceChallenge {

    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static String addRandomMiddleInitial(String name) {
        char initial = (char) ('A' + new Random().nextInt(26));
        return name + " " + initial + ".";
    }

    public static String addReversedLastName(String name) {
        String first = name.split(" ")[0];
        return name + " " + reverseString(first);
    }

    public static void transformNames(String[] names, List<UnaryOperator<String>> transformers) {
        for (int i = 0; i < names.length; i++) {
            String result = names[i];
            for (UnaryOperator<String> op : transformers) {
                result = op.apply(result);
            }
            names[i] = result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of names: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        String[] names = new String[count];

        for (int i = 0; i < count; i++) {
            System.out.print("Enter name #" + (i + 1) + ": ");
            names[i] = scanner.nextLine();
        }

        List<UnaryOperator<String>> transformers = Arrays.asList(
            String::toUpperCase,
            MethodReferenceChallenge::addRandomMiddleInitial,
            MethodReferenceChallenge::addReversedLastName
        );

        transformNames(names, transformers);

        System.out.println("\nTransformed names:");
        for (String name : names) {
            System.out.println(name);
        }

        scanner.close();
    }
}
