package challenges_4th_july;

import java.util.stream.*;
import java.util.*;

public class BingoStreamExample {
    public static void main(String[] args) {
        
        // B1-B15 letter stream
//    	Stream<String> bStream = Stream.iterate(1,i->i<=15 ,i -> i + 1)
//                .map(i -> "B" + i);    		
    	String arr[]=new String[15];
    	for(int i=0;i<15;i++) {
    		arr[i]="B"+(i+1);
    	}
    	
    	Stream<String> bStream=Arrays.stream(arr);
        // I16-I30
        List<String> iList = IntStream.range(16, 31)
                .mapToObj(i -> "I" + i)
                .collect(Collectors.toList());
        Stream<String> iStream = iList.stream();
        
        
//        Stream<String> snew=Stream.generate(null)

        // N31-N45
        Stream<String> nStream = Stream.iterate(31, i -> i + 1)
                .limit(15)
                .map(i -> "N" + i);

        // G45-G60
        Stream<String> gStream = IntStream.rangeClosed(46, 60)
                .mapToObj(i -> "G" + i);

        // O61-O75
        Stream<String> oStream = Stream.iterate(61, i -> i + 1)
                .limit(15)
                .map(i -> "O" + i);

        // Concatenate all streams and print
        Stream.concat(
            Stream.concat(
                Stream.concat(
                    Stream.concat(bStream, iStream), 
                    nStream),
                gStream),
            oStream)
        .forEach(System.out::println);
    }
}