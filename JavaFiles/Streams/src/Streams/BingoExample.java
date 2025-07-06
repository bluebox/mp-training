package Streams;

import java.util.stream.IntStream;

public class BingoExample {

	public static void main(String[] args) {
		String bingo = "BINGO";    
		
		IntStream.range(0, bingo.length())
		.forEach(i -> {
			char letter = bingo.charAt(i);
			int start = 1 + i * 15;
			int end = start + 14;
			System.out.println(letter+String.valueOf(start) + " - " + letter + String.valueOf(end));
		});
		
		
//        IntStream.range(0, bingo.length())
//            .boxed()
//            .flatMap(i -> {
//                char letter = bingo.charAt(i);
//                int start = 1 + i * 15;
//                int end = start + 14;
//                return IntStream.rangeClosed(start, end)
//                        .mapToObj(n -> letter + String.valueOf(n));
//            }).forEach(System.out::println);
        
        
		}
}

