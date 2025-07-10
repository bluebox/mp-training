package Day11_10_07_practice;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTerminalOperationsExample {
    public static void main(String[] args) {
        // Sample data
        List<String> names = Arrays.asList(
            "Reflection", "Collection", "Stream",
            "Structure", "Sorting", "State"
        );
        System.out.println("forEach:");
        names.stream().forEach(System.out::println);

        List<String> sNames = names.stream()
                                   .filter(name -> name.startsWith("S"))
                                   .collect(Collectors.toList());
        System.out.println("\ncollect (names starting with 'S'):");
        sNames.forEach(System.out::println);

        String concatenatedNames = names.stream().reduce(
            "",
            (partialString, element) -> partialString + " " + element
        );
        System.out.println("\nreduce (concatenated names):");
        System.out.println(concatenatedNames.trim());
        long count = names.stream().count();
        System.out.println("\ncount:");
        System.out.println(count);
        Optional<String> firstName = names.stream().findFirst();
        System.out.println("\nfindFirst:");
        firstName.ifPresent(System.out::println);
        boolean allStartWithS = names.stream().allMatch(
            name -> name.startsWith("S")
        );
        System.out.println("\nallMatch (all start with 'S'):");
        System.out.println(allStartWithS);
        boolean anyStartWithS = names.stream().anyMatch(
            name -> name.startsWith("S")
        );
        System.out.println("\nanyMatch (any start with 'S'):");
        System.out.println(anyStartWithS);
    }
}