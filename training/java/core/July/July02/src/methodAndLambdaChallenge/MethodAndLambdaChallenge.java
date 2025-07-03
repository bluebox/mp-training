package methodAndLambdaChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class MethodAndLambdaChallenge {

    public static void main(String[] args) {

        String[] names = {"Satheesh", "ram", "sampath","kalyan"};

        List<UnaryOperator<String>> transformations = new ArrayList<>();
        
        transformations.add(String::toUpperCase);
 
        transformations.add(name -> {
            char middleInitial = (char) ('A' + new Random().nextInt(26));
            return name + " " + middleInitial + ".";
        });
 
        transformations.add(name -> {
            String[] parts = name.split(" ");
            String firstName = parts[0];
            String reversed = new StringBuilder(firstName).reverse().toString();
            return name + " " + reversed;
        });
        
        System.out.println("initial List:");
        Arrays.asList(names).forEach(System.out::println);
        
        applyTransformations(names, transformations);
        
        System.out.println("After Appplying transformations List:");
        Arrays.asList(names).forEach(System.out::println);
    }

    public static void applyTransformations(String[] names, List<UnaryOperator<String>> functions) {
        for (int i = 0; i < names.length; i++) {
            String updatedName = names[i];
            for (UnaryOperator<String> func : functions) {
                updatedName = func.apply(updatedName);
            }
            names[i] = updatedName;
        }
    }
}
