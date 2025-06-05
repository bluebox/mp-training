package Streams;

import java.util.*;
import java.util.stream.*;

public class BingoStreamChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream<String> bStream=IntStream.rangeClosed(1,15).mapToObj(i->"B"+i);
		
		Stream<String> iStream=Stream.iterate(16, i->++i).limit(15).map(i->"I"+i);
		
		Integer[] nNums=IntStream.rangeClosed(31,45).boxed().toArray(Integer[]::new);
		
		Stream<String> nStream=Arrays.stream(nNums).map(i->"N"+i);
		
		List<Integer> gList=IntStream.rangeClosed(46,60).boxed().collect(Collectors.toList());
		
		Stream<String> gStream=gList.stream().map(i->"G"+i);
		
		Stream.Builder<String> oBuilder=Stream.builder();
		
		for(int i=61;i<=75;i++)
		{
			oBuilder.add("O"+i);
		}
		
		Stream<String> oStream=oBuilder.build();
		
		Stream<String> finalStream=Stream.concat(
				Stream.concat(
						Stream.concat(
								Stream.concat(bStream, iStream), nStream), gStream), oStream);

		finalStream.forEach(System.out::println);
		
	}

}
