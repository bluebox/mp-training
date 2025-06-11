import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.StreamSupport;

public class BingoStreamChallenge {
    public static void main(String[] args) {
        // Stream 1: B1-B15 using IntStream.range
        Stream<String> stream1 = IntStream.rangeClosed(1, 15).mapToObj(i -> "B" + i);

        // Stream 2: I16-I30 using Stream.iterate with limit
        Stream<String> stream2 = Stream.iterate(16, n -> n + 1)
                                       .limit(15)
                                       .map(n -> "I" + n);
        

        // Stream 3: N31-N45 using array and Stream.of
        Stream<String> stream3 = Stream.of(31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45)
                                       .map(n -> "N" + n);

        // Stream 4: G46-G60 using Iterator
        Iterator<String> iterator = new Iterator<>() {
            private int current = 46;
            public boolean hasNext() { return current <= 60; }
            public String next() { return "G" + current++; }
        };
        Stream<String> stream4 = StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);

        // Stream 5: O61-O75 using Stream.generate with explicit Supplier<String>
        Supplier<String> oSupplier = new Supplier<>() {
            private int current = 61;
            public String get() {
                if (current > 75) throw new IllegalStateException();
                return "O" + current++;
            }
        };
        Stream<String> stream5 = Stream.generate(oSupplier).limit(15);

        // Concatenate all streams step-by-step
        Stream<String> all = Stream.concat(stream1, stream2);
        all = Stream.concat(all, stream3);
        all = Stream.concat(all, stream4);
        all = Stream.concat(all, stream5);

        all.forEach(System.out::println);
    }
}