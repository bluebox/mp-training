package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayListStream {
	public static void main(String[] args) {
		ArrayList<Integer> lst=new ArrayList<>(List.of(45,67,47,39,12,31));
		System.out.println(lst.stream().allMatch((n)->n%3==0));
		System.out.println(lst.stream().anyMatch((n)->n%3==0));
		lst.stream().sorted().forEach(n->System.out.print(n+" "));
		
		System.out.println();
		lst.stream()
		.skip(2).sorted().forEach(n->System.out.print(n+" "));
		lst.stream()
		.skip(2)
		.sorted().forEach(n->System.out.print(n+" "));
		
		System.out.println();
		Random r=new Random();
		int sum=IntStream.generate(()->r.nextInt(1,35))
		.map(n->n+1)
		.peek(n->System.out.print(n+" "))
		.limit(50).sum();
		System.out.println("\nSum of Random number "+sum);
		
		List<Integer> arrlist=IntStream.generate(()->r.nextInt(1,35))
				.map(n->n+1)
				.limit(10).boxed()
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(arrlist);
		
	}

}
