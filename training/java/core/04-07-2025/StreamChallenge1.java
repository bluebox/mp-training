import java.util.stream.*;
import java.util.*;

public class StreamChallenge1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream<String> b=Stream.iterate(1, i->i<=15, i->i+1)
				.map(i->"B"+i);
		Stream<String> i=Stream.iterate(16,i->i<=30,i->i+1)
				.map(i->"I"+i);		
		Stream<String> n=Stream.iterate(31,i->i<=45,i->i+1)
				.map(i->"N"+i);	
		Stream<String> g=Stream.iterate(46,i->i<=60,i->i+1)
				.map(i->"G"+i);
		Stream<String> o=Stream.iterate(61,i->i<=75,i->i+1)
				.map(a->"O"+a);
		Stream.concat(Stream.concat(Stream.concat(Stream.concat(b,i),n),g),o).forEach(System.out::println);
		
	}

}
