package streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		var even=IntStream.iterate(0,i->i+1);

		even.limit(100);
		even.filter((a)->a%2==0)
		.forEach(System.out::println);
		
		
	}

}
