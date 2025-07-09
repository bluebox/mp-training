package day8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		Random random = new Random();
		for (int i = 1; i <= 20; i++) {
			arr.add(random.nextInt(100));
		}
		System.out.println(arr);
		List<Integer> res = arr.stream().sorted().map(e -> e * e).filter(e -> e % 2 == 0).collect(Collectors.toList());// .forEach(System.out::println);
		System.out.print(res);
//		Stream<Integer> stream = Stream.iterate(1, i -> i <= 20, i -> i += 1);
//		Stream<Integer> stream= Stream.iterate(1,i -> i <= 20, i -> i * 2);
		// create a stream using iterate
        Stream<Integer> stream
            = Stream.iterate(1, i -> i * 2).limit(15);

       
        // print Values
        stream.forEach(System.out::println);

	}

}
