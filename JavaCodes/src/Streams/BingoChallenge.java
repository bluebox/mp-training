package Streams;

import java.util.*;
import java.util.stream.IntStream;

public class BingoChallenge {

	public static void main(String[] args) {
		int cnt=0;
		List<Character>ans=new ArrayList<>(Arrays.asList('B','I','N','G','O'));
		for(Character ele:ans) {
			IntStream.range(1, 16)
			    .forEach(i->System.out.print(ele+"-"+i+" "));
			System.out.println();
		}
	}

}
