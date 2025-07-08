import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamChallenge {
	public static void main(String[] args) {
			
		Stream<String> bstream=
				IntStream
				.rangeClosed(1,15)
				.mapToObj(i->"B"+i);
		
		Stream<String> istream=
				IntStream
				.rangeClosed(16,30)
				.mapToObj(i->"I"+i);
		
		Stream<String> nstream=
				IntStream
				.rangeClosed(30,45)
				.mapToObj(i->"N"+i);
		
		Stream<String> gstream=
				IntStream
				.rangeClosed(45,60)
				.mapToObj(i->"G"+i);
		
		
		Stream<String> ostream=
				IntStream
				.rangeClosed(60,75)
				.mapToObj(i->"O"+i);
		
		Stream<String> bingo=Stream.concat(Stream.concat(bstream, istream), Stream.concat(nstream, Stream.concat(gstream, ostream)));
		bingo.forEach(System.out::println);
	}
}


