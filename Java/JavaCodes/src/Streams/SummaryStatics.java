package Streams;

import java.util.*;
import java.util.stream.IntStream;

public class SummaryStatics {

	public static void main(String[] args) {
		IntSummaryStatistics val=IntStream.range(0, 1000)
	        .skip(10)
	     	.summaryStatistics();
		System.out.println(val);
	}

}
