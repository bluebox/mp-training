package Lambda_Challenge;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class LambdaChallenge {
    public static void main(String[] args) {
        //ArrayList<String> arr = new ArrayList<>(Arrays.asList("hello" , "how" , "are","you"));

        List<String> words = Arrays.asList("hello", "world", "lambda", "java");

        List<String> upperCaseWords = words.stream()
                                           .map(word -> word.toUpperCase())
                                           .collect(Collectors.toList());

        upperCaseWords.forEach(System.out::println);

        //System.out.println(upperCaseWords);
        //List.replaceAll(upperCaseWords , "change");
        upperCaseWords.replaceAll(s -> "change");
        upperCaseWords.forEach(System.out::println);

        //System.out.println(arr);
    }
}
