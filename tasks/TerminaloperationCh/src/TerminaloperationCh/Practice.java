package TerminaloperationCh;
import java.util.*;

public class Practice {
	public static void main(String[] args) {
		List<Integer> numbers=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		int sum=numbers.stream()
				.filter(n->n%2==0)
				.map(n->n*n)
				.reduce(0, Integer::sum);
		System.out.println(sum);
	}

}
