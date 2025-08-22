package streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamChallenge {
	
    public static void main(String[] args) {

        var bstr = IntStream.range(1, 16)
                            .mapToObj(i -> "B" + i);
        
        System.out.println("================================");
        bstr.forEach(System.out::println);

        var istr = IntStream.rangeClosed(16, 30)
                            .mapToObj(i -> "I" + i);
        System.out.println("================================");
        istr.forEach(System.out::println);

        var nstr = Stream.of(31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45)
                         .map(i -> "N" + i);
        System.out.println("================================");
        nstr.forEach(System.out::println);

        var gstr = IntStream.rangeClosed(46, 60)
                            .mapToObj(i -> "G" + i);
        System.out.println("=================================");
        gstr.forEach(System.out::println);
        
        var ostr = IntStream.iterate(61,i->i<=75,i->i+1)
                            .mapToObj(i -> "O" + i);
        System.out.println("=================================");
        ostr.forEach(System.out::println);
    }
}
