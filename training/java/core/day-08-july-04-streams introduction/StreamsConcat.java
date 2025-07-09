package day8;

import java.util.stream.Stream;

public class StreamsConcat {
	public static void main(String[] args) {
		Stream<String> bStream=Stream
				.iterate(1, i->i+1)
				.limit(15)
				.map(i->"B"+i);
		
		Stream<String> iStream=Stream
				.iterate(16, i->i+1)
				.limit(15)
				.map(i->"I"+i);
		
		Stream<String> nStream=Stream
				.iterate(31, i->i+1)
				.limit(15)
				.map(i->"N"+i);
		
		Stream<String> gStream=Stream
				.iterate(46, i->i+1)
				.limit(15)
				.map(i->"G"+i);
		
		Stream<String> oStream=Stream
				.iterate(61, i->i+1)
				.limit(15)
				.map(i->"O"+i);
		
		Stream<String> result = 
				Stream.concat(bStream, 
					Stream.concat(iStream, 
							Stream.concat(nStream, 
									Stream.concat(gStream, oStream))));
		result.forEach(System.out::println); 
	}
}