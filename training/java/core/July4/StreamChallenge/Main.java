package July4.StreamChallenge;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		/*
		 * IntStream.iterate((int) 'A', i -> i <= (int)'z' , i -> i+1) .filter(Character
		 * :: isAlphabetic) .forEach(System.out::println);
		 */

		List<String> blist = Stream.iterate(1, i -> i <= 15, i -> i + 1)
								   .map(i -> "B" + i)
								   .toList();
//		blist.forEach(System.out::println);

		List<String> ilist = Stream.iterate(16, i -> i <= 30, i -> i + 1)
								   .map(i -> "I" + i)
								   .toList();
//		ilist.forEach(System.out::println);

		List<String> nlist = Stream.iterate(31, i -> i <= 45, i -> i + 1)
								   .map(i -> "N" + i)
								   .toList();
//		nlist.forEach(System.out::println);

		List<String> glist = Stream.iterate(46, i -> i <= 60, i -> i + 1)
								   .map(i -> "G" + i)
								   .toList();
//		glist.forEach(System.out::println);

		List<String> olist = Stream.iterate(61, i -> i <= 75, i -> i + 1)
								   .map(i -> "O" + i)
								   .toList();
//		olist.forEach(System.out::println);
		
		Stream.of(blist, ilist, nlist, glist, olist)
			  .forEach(System.out::println);

	}
}
