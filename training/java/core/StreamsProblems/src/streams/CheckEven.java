package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CheckEven {
	public static void main(String args[]) {
		List<Integer> values= Arrays.asList(2,34,43,32,65,23);
		List<Integer> streamvals=values.stream().filter(n -> n%2==0).collect(Collectors.toList());
		System.out.println(streamvals);
	}
}
