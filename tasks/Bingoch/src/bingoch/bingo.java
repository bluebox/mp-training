package bingoch;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
public class bingo {
	public static void main(String[] args) {
		Stream<String> sb=Stream.of("B1","B2","B3","B4","B5","B6","B7","B8","B9","B10","B11","B12","B13","B14","B15");
		Stream<String> si=IntStream.range(16,31).mapToObj(i->"I"+i);
		Stream<String> sn=IntStream.range(31, 46).mapToObj(i->"N"+i);
		Stream<String> sg=IntStream.range(46,61).mapToObj(i->"G"+i);
		Stream<String> so=IntStream.range(61,76).mapToObj(i->"O"+i);
		Stream<String> combinedStream=Stream.concat(Stream.concat(Stream.concat(sb,si), sn),Stream.concat(sg,so));
		combinedStream.forEach(System.out::println);
	}
}
