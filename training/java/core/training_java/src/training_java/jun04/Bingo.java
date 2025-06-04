package FinalandStreams;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Bingo {
	public static void main(String[] args) {
	Stream <String> bstream=IntStream.iterate(1,i->i+1).limit(15).mapToObj(i->"B"+i);
	Stream<String>  istream=IntStream.rangeClosed(16,30).boxed().map(i->"I"+i);
	String[] nString= {"N31","N32","N33","N34","N35","N36","N37","N38","N39","N40","N41","N42","N43","N44","N45"};
	Stream<String> nstream=Stream.of(nString);
	Integer[] gnums=IntStream.iterate(46,i->i+1).limit(15).mapToObj(i->Integer.valueOf(i)).toArray(Integer[]::new);
	Stream<String> gstream=Arrays.stream(gnums).map(i->"G"+i);
	Stream<String> ostream=Stream.iterate(61,i->i+1).limit(15).map(i->"O"+i);
	Stream<String> bingostream=Stream.of(bstream,istream,nstream,gstream,ostream).flatMap(s->s);
	
	bingostream.forEach(s->System.out.println(s));
	}
	

}
