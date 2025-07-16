package streams;
import java.util.stream.*;
public class StreamSourceChallenge {
	
	public static void main(String[] args) {
		
		Stream<String> str=Stream.of("Six");
		Stream<String> str2=Stream.of("Six");
		Stream.concat(str, str2)
		     .forEach(System.out::println);
		
	}
}
