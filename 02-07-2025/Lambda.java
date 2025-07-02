package com.prana;

import java.util.*;
import java.util.function.UnaryOperator;

public class Lambda {

    private static final Random random = new Random();

    public static void main(String[] args) {
        String[] names = {"alice", "Bob", "cHarLie", "dEbOrAh"};
        
       
        List<UnaryOperator<String>> transformations = Arrays.asList(
            
            String::toUpperCase,
            name -> name + " " + (char) ('A' + random.nextInt(26)) + ".",
            Lambda::addReversedLastName,
            name -> "  " + name + "  ",
            name -> name.replaceAll("(?i)[aeiou]", "*"),
            Lambda::appendLength
        );

        applyTransformations(names, transformations);

        System.out.println("Transformed Names:");
        Arrays.stream(names).forEach(System.out::println);
    }

   
    public static void applyTransformations(String[] names, List<UnaryOperator<String>> functions) {
        for (int i = 0; i < names.length; i++) {
            String result = names[i];
            for (UnaryOperator<String> function : functions) {
            	result = function.apply(result);
            }
            names[i] = result; 
        }
    }

    public static String addReversedLastName(String name) {
        String[] parts = name.split(" ");
        String firstName = parts[0];
        String reversed = new StringBuilder(firstName).reverse().toString();
        return name + " " + reversed;
    }

    public static String appendLength(String name) {
        return name + " (" + name.length() + ")";
    }
}
