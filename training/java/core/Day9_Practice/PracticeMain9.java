package Day9_Practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Stream;

public class PracticeMain9 {
	public static void main(String[] args) {
		int[] prac=new int[] {8,9,7,1,5,6,3,4};
		Arrays.stream(prac).forEach(System.out::print);
		System.out.println("\n**************************");
		Stream.of(8,9,7,5,4,6,4).map(i->i*2).forEach(System.out::print);
		System.out.println("\n**************************");
		Stream.iterate(0,i->i+1).limit(prac.length).forEach(i->System.out.print(prac[i]+" "));
		System.out.println("\n**************************");
		Stream.iterate(0,i->i<prac.length,i->i+1).forEach(i->System.out.print(prac[i]+" "));
		System.out.println("\n**************************");

		//Stream.generate(()->System.out.printf("%d ".formatted(prac[i++]))).limit(prac.length);
		Stream.generate(()->new Random().nextInt(0, prac.length))
				.limit(1000)
				.sorted()
				.distinct()
				.limit(prac.length)
				.map(i->prac[i])
				.forEach(System.out::print);
		
	}
}
