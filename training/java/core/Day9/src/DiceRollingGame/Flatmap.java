package DiceRollingGame;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Flatmap {
    public static void main(String[] args) {
        List<List<Integer>> listOfLists = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4, 5),
            Arrays.asList(6)
        );

        List<Integer> flattenedList = listOfLists.stream()
            .flatMap(List::stream) 
            .collect(Collectors.toList());

        System.out.println(flattenedList);
}
}