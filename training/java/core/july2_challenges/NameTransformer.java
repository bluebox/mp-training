package june2_challenges;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class NameTransformer {

    public static void main(String[] args) {

        String[] names = {"Alice", "boB", "charLie", "daVid", "eVe"};

        List<UnaryOperator<String>> operations = new ArrayList<>();

        operations.add(String::toUpperCase);

        operations.add(name -> {
            char middleInitial = (char) ('A' + new Random().nextInt(26));
            return name + " " + middleInitial + ".";
        });

        operations.add(name -> {
            String firstName = name.split(" ")[0]; 
            String lastName = new StringBuilder(firstName).reverse().toString();
            return name + " " + lastName;
        });

        operations.add(name -> {
            String firstName = name.split(" ")[0];
            if (firstName.length() % 2 == 0)
                return "Ms. " + name;
            else
                return "Mr. " + name;
        });

        operations.add(NameTransformer::replaceVowelsWithStar);

        operations.add(name -> name + ", The Great");

       transformNames(names, operations);
       
       List<String> nameList = Arrays.asList(names);
       nameList.forEach(System.out::println);

    }

    public static void transformNames(String[] names, List<UnaryOperator<String>> operations) {
        for (int i = 0; i < names.length; i++) {
            String updatedName = names[i];
            for (UnaryOperator<String> operation : operations) {
            	updatedName = operation.apply(updatedName);  
            }
            names[i] = updatedName;
        }
    }
    
   

    public static String replaceVowelsWithStar(String str) {
        return str.replaceAll("[AEIOUaeiou]", "*");
    }
}
