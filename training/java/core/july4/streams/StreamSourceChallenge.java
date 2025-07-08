package dev.tulasidhar.july4.streams;

import java.util.stream.Stream;
import java.util.stream.IntStream;

public class StreamSourceChallenge {
    public static void main(String[] args) {
        
        Stream<String> stream1 = IntStream.rangeClosed(1, 15)
            .mapToObj(i -> "B" + i);
        

        Stream<String> stream2 = IntStream.range(16, 31)
            .mapToObj(i -> "I" + i);
        

        Stream<String> stream3 = Stream.iterate(31, i -> i <= 45, i -> i + 1)
            .map(i -> "N" + i);
        

        Stream<String> stream4 = Stream.generate(new java.util.concurrent.atomic.AtomicInteger(45)::getAndIncrement)
            .limit(16)
            .map(i -> "G" + i);
        
        Stream<String> stream5 = Stream.of(
            "O61", "O62", "O63", "O64", "O65", "O66", "O67", "O68", "O69", "O70",
            "O71", "O72", "O73", "O74", "O75"
        );
        
        Stream<String> concatenatedStream = Stream.concat(
            Stream.concat(
                Stream.concat(stream1, stream2),
                Stream.concat(stream3, stream4)
            ),
            stream5
        );
        
        concatenatedStream.forEach(System.out::println);
    }
}