package streams;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SumOfArray {
	public static void main(String[] args) {
		int[] arr= {3,4,5,6,2,1};
		IntStream str=Arrays.stream(arr);
		System.out.println(str.sum());
	}

}
