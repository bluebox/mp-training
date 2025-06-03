import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsPracticeChallenge {
	
	static boolean isPrime(int n) {
		boolean flag = true;
		for(int i=2;i<n;i++) {
			if(n%i==0) flag = false;
		}
		return n>1 && flag;
	}

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9,10));
		list.stream()
			.filter(s -> s%3 == 0)
			.forEach(System.out::println);
		
		var stream1 = list.stream()
						  .filter(s -> s%2 == 0);
		
		var stream2 = list.stream()
						  .filter(s -> s%2 != 0);
		
		var stream3 = Stream.concat(stream1, stream2);
		stream3.forEach(s->System.out.print(s + " "));

		int[] arr = {1, 6, 1, 2, 10, 6};
		Arrays.stream(arr)
			  .filter(s -> s < 8)
			  .sorted()
			  .forEach(System.out::println);
		
		System.out.println();
		
		Stream.iterate(0, i->i+1)
			  .filter(StreamsPracticeChallenge::isPrime)
			  .limit(20)
			  .forEach(System.out::println);
	}

}
