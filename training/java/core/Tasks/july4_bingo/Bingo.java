package corejava.july4_bingo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;

public class Bingo {
    public static void main(String[] args) {
        Stream<String> bStream = Stream.iterate(1, n -> n + 1)
                .limit(15)
                .map(n -> "B" + n);

        Stream<String> iStream = IntStream.rangeClosed(16, 30)
                .mapToObj(n -> "I" + n);

        Integer[] nNumbers = new Integer[15];
        for (int i = 0; i < 15; i++) {
            nNumbers[i] = i +31;
        }
        
        Stream<String> nStream = Stream.of(nNumbers)
                .map(n -> "N" + n);

        Stream.Builder<String> gBuilder = Stream.builder();
        for (int i = 46; i <= 60; i++) {
            gBuilder.add("G" + i);
        }
        Stream<String> gStream = gBuilder.build();

        AtomicInteger counter = new AtomicInteger(61);
        Stream<String> oStream = Stream.generate(() -> "O" + counter.getAndIncrement())
                .limit(15);

        Stream<String> bingoStream = Stream.concat(
                Stream.concat(bStream, iStream),
                Stream.concat(nStream, Stream.concat(gStream, oStream))
        );

        bingoStream.forEach(System.out::println);
    }
}
