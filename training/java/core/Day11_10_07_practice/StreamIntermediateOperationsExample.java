package Day11_10_07_practice;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamIntermediateOperationsExample {
    public static void main(String[] args) {
        List<List<String>> listOfLists = Arrays.asList(
            Arrays.asList("Reflection", "Collection", "Stream"),
            Arrays.asList("Structure", "State", "Flow"),
            Arrays.asList("Sorting", "Mapping", "Reduction", "Stream")
        );
        Set<String> intermediateResults = new HashSet<>();
        List<String> result = listOfLists.stream()
            .flatMap(List::stream)               
            .filter(s -> s.startsWith("S"))      // Filter elements starting with "S"
            .map(String::toUpperCase)            // Transform each element to uppercase
            .distinct()                          // Remove duplicate elements
            .sorted()                            // Sort elements
            .peek(s -> intermediateResults.add(s)) // Perform an action (add to set) on each element
            .collect(Collectors.toList());       // Collect the final result into a list

        // Print the intermediate results
        System.out.println("Intermediate Results:");
        intermediateResults.forEach(System.out::println);

        // Print the final result
        System.out.println("Final Result:");
        result.forEach(System.out::println);
    }
}