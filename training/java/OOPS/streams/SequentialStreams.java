package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SequentialStreams {

	public static void main(String[] args) {
		IntStream.iterate(10,i-> i+1)
				.filter(n-> n%2==0)
				.limit(10)
				.forEach(System.out::println);
				;

				System.out.println();
				long count=Stream.iterate(1,i->i=i+1)
				.map(i -> i*2)
				.filter(i-> i%20 == 0)
				.count();
				System.out.println(count);
				
		List<String> names= new ArrayList<>(Arrays.asList("ramsai","raghu","rakesh","manoj","mourya","dsp"));
		
		
		names.stream().map(i-> i.substring(0,1).toUpperCase()+i.substring(1).toLowerCase())
		.forEach(System.out::println);
		
	}


}
