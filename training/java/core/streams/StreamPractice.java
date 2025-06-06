package streams;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice {
    public static void main(String[] args) {
        List<String> arr = Arrays.asList("naga" , "bhushan" , "saketh" , "sameer" , "" , "");

        var count = arr.stream().filter(s -> s.isEmpty()).count();
        System.out.println("empty strings count : " + count);

        String mergerdString = arr.stream().reduce("",(s1,s2) -> s1 + s2);
        System.out.println(mergerdString);

        List<String> newList = arr.stream().map(s -> s + "_hello").collect(Collectors.toList());
        System.out.println(newList);

        List<Integer> nums = Arrays.asList(3,2,1,5,4,6,7,8,3,1);

        List<Integer> unique_elements = nums.stream().distinct().collect(Collectors.toList());
        System.out.println(unique_elements);

        List<String> list = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "date");
    Set<String> duplicates = list.stream()
    .collect(Collectors.groupingBy(
        Function.identity(), Collectors.counting()
    ))
    .entrySet().stream() 
    .filter(entry -> entry.getValue() > 1)
    .map(Map.Entry::getKey)
    .collect(Collectors.toSet());

    System.out.println(duplicates);  // Output: [apple, banana]


    }
}
