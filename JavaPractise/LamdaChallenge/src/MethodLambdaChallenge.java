import java.util.*;
import java.util.function.UnaryOperator;

public class MethodLambdaChallenge {

    public static void main(String[] args) {
        String[] names = {"Shiv", "Krishna", "Vishnu", "Brahma"};

        List<UnaryOperator<String>> transformers = new ArrayList<>();

        transformers.add(String::toUpperCase);

        transformers.add(name -> {
            char middleInitial = (char) ('A' + new Random().nextInt(26));
            return name + " " + middleInitial + ".";
        });

        transformers.add(name -> {
            String first = name.split(" ")[0];
            String last = new StringBuilder(first).reverse().toString();
            return name + " " + last;
        });

        transformers.add(MethodLambdaChallenge::addTitle);

        transformers.add(name -> name + " (" + name.replaceAll("\\s+", "").length() + ")");

        transformNames(names, transformers);

        System.out.println("Transformed Names:");
        for (String name : names) {
            System.out.println(name);
        }
    }

    public static void transformNames(String[] names, List<UnaryOperator<String>> functions) {
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            for (UnaryOperator<String> func : functions) {
                name = name.transform(func); 
            }
            names[i] = name;
        }
    }

    public static String addTitle(String name) {
        String first = name.split(" ")[0].toLowerCase();
        if (first.endsWith("a") || first.endsWith("e")) {
            return "Ms. " + name;
        } 
        else {
            return "Mr. " + name;
        }
    }
}