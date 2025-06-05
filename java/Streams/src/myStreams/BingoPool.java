package myStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BingoPool {
	public static void main(String[] args) {
//	int limit=75;
//	int[] pool[] ;
//	for(int i=0;i<75;i++) {
//		pool.add(i+1);
//	}

		Stream<String> bStream = IntStream.rangeClosed(1, 15).mapToObj(s -> "B" + s);
		Stream<String> iStream = IntStream.rangeClosed(16, 30).mapToObj(s -> "I" + s);
		Stream<String> nStream = IntStream.rangeClosed(31, 45).mapToObj(s -> "N" + s);
		Stream<String> gStream = IntStream.rangeClosed(46, 60).mapToObj(s -> "G" + s);
		Stream<String> oStream = IntStream.rangeClosed(61, 75).mapToObj(s -> "O" + s);
		Stream bingoStream = Stream
				.concat(Stream.concat(Stream.concat(Stream.concat(bStream, iStream), nStream), gStream), oStream);
		System.out.println("------------------------");
		bingoStream
		.filter(t -> t.toString().charAt(0)=='N')
		.filter(t -> t.toString().charAt(1)=='3')
//		.allMatch(t -> t.toString().charAt(1)=='1') _
//		.count()
		.forEach(System.out::println);

	}
}
