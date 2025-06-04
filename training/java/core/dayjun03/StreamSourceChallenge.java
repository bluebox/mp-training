package dayjun03;


import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class StreamSourceChallenge {
    public static void main(String[] args) {
        // 1. Collection.stream()
        List<String> collection = List.of("A1", "A2", "A3");
        Stream<String> stream1 = collection.stream();

        // 2. Arrays.stream()
        String[] array = {"B1", "B2", "B3"};
        Stream<String> stream2 = Arrays.stream(array);

        // 3. Stream.of()
        Stream<String> stream3 = Stream.of("C1", "C2", "C3");

        // 4. Stream.iterate() infinite
        Stream<Integer> stream4 = Stream.iterate(1, n -> n + 1).limit(3);

        // 5. Stream.iterate() finite
        Stream<Integer> stream5 = Stream.iterate(1, n -> n <= 3, n -> n + 1);

        // 6. Stream.generate()
        Supplier<String> supplier = new Supplier<>() {
            private int count = 1;
            public String get() {
                return "F" + count++;
            }
        };
        Stream<String> stream6 = Stream.generate(supplier).limit(3);

        // 7. IntStream.range()
        IntStream stream7 = IntStream.range(1, 4);

        // 8. IntStream.rangeClosed()
        IntStream stream8 = IntStream.rangeClosed(1, 3);

        // Concatenate all streams
        Stream.concat(
            Stream.concat(
                Stream.concat(stream1, stream2),
                Stream.concat(stream3, stream4.map(n -> "D" + n))
            ),
            Stream.concat(
                Stream.concat(stream5.map(n -> "E" + n), stream6),
                Stream.concat(stream7.mapToObj(n -> "G" + n), 
                             stream8.mapToObj(n -> "H" + n))
            )
        ).forEach(System.out::println);
    }
}
