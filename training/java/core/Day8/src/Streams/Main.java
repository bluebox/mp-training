package Streams;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Stream<String> bStream = IntStream.rangeClosed(1, 15)
                .mapToObj(i -> "B" + i);

        Stream<String> iStream = IntStream.rangeClosed(16, 30)
                .mapToObj(i -> "I" + i);

        Stream<String> nStream = IntStream.rangeClosed(31, 45)
                .mapToObj(i -> "N" + i);

        Stream<String> gStream = IntStream.rangeClosed(46, 60)
                .mapToObj(i -> "G" + i);

        Stream<String> oStream = IntStream.rangeClosed(61, 75)
                .mapToObj(i -> "O" + i);

        List<String> bingoLabels = Stream.of(bStream, iStream, nStream, gStream, oStream)
                .flatMap(s -> s)
                .collect(Collectors.toList());

        System.out.println("All BINGO Labels:");
        bingoLabels.forEach(System.out::println);

        System.out.println("\n Random 5x5 BINGO Grid:\n");

        List<String> grid = new Random()
                .ints(1, 76)
                .distinct()
                .limit(25)
                .mapToObj(i -> {
                    if (i <= 15) return "B" + i;
                    else if (i <= 30) return "I" + i;
                    else if (i <= 45) return "N" + i;
                    else if (i <= 60) return "G" + i;
                    else return "O" + i;
                })
                .collect(Collectors.toList());

        for (int i = 0; i < 25; i++) {
            System.out.printf("%-4s", grid.get(i));
            if ((i + 1) % 5 == 0) {
                System.out.println(); 
            }
        }
    }
}
