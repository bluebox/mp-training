package com.prana;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Mestream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int s=15;
		Stream<Object> bstream = Stream.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
		    .map(i -> "B" + i);
		
		s=16;
		var istream = Stream.iterate(s,i -> i + 1)
				.limit(15)
				.map(i -> "I" + i );
		
		s+=15;
		int ns = s;
		String[] ol = new String[15];
		Arrays.setAll(ol, i -> "N" + (ns + i));
		var nstream = Arrays.stream(ol);
		
		s+=15;
		var gstream = Stream.iterate(s,i -> i + 1)
				.limit(15)
				.map(i -> "G" + i );
		
		s+=15;
		final int startO = s;
        Supplier<Integer> oSupplier = new Supplier<>() {
            private int current = startO;

            @Override
            public Integer get() {
                return current++;
            }
        };

        Stream<String> ostream = Stream.generate(oSupplier)
                .limit(15)
                .map(i -> "O" + i);

        
        var bistream = Stream.concat(bstream,istream);
        var ngstream = Stream.concat(nstream, gstream);
        var bingstream = Stream.concat(bistream, ngstream);
        var bingostream = Stream.concat(bingstream, ostream);
        bingostream.forEach(System.out::println);
    }
}
