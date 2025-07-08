package streamSourceChallenge;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class StreamChallenge {
	public static void main(String[] args) {
		Stream<String> streamB=Stream.iterate(1, i-> i + 1)
				.limit(15)
				.map(i-> (" B"+i) );
		streamB=Stream.concat(streamB,Stream.of("\n"));
		
		 Stream<String> streamI =
				 Stream.iterate(16,i -> i <= 30,i->i+1)
				 .map(i-> " I"+i);
		 streamI=Stream.concat(streamI,Stream.of("\n"));
		 
		Random random = new Random();
		Stream<String> streamN = Stream.iterate(31, i-> i< 46,i->random.nextInt(31,46))
				.distinct()
				.limit(15)
				.map(i -> " N"+i)
				.sorted();
		streamN=Stream.concat(streamN,Stream.of("\n"));
		
		 Stream<String> streamG = 
				 Arrays.asList(" G46"," G47"," G48"," G49"," G50"," G51"," G52"," G53"," G54"," G55"," G56"," G57"," G58"," G59"," G60")
		 		.stream();
		 streamG=Stream.concat(streamG,Stream.of("\n"));
		 
		 Stream<String> streamO=
		 Stream.of(" O61", " O62", " O63"," O64", " O65", " O66"," O67", " O68", " O69"," O70", " O71", " O72"," O73", " O74", " O75");
		 
		 Stream<String> streamBI = Stream.concat(streamB, streamI);
		 Stream<String> streamBIN = Stream.concat(streamBI,streamN);
		 Stream<String> streamGO = Stream.concat(streamG,streamO);
		 Stream.concat(streamBIN,streamGO).forEach(System.out::print);
	}
}
