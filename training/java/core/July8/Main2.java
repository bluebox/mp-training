package July8;

import java.util.Random;

public class Main2 {
	
	public static void main(String[] args) {
		
		System.out.printf("%1$d = %1$c%n", (int)(Math.random()*26)+65);
		Random r = new Random();
		System.out.printf("%1$d = %1$c%n", r.nextInt(65, 91));
		System.out.printf("%1$d%n", r.nextInt());
		
		long nano = System.nanoTime();
		System.out.println(nano);
		Random pseudoRandom = new Random(nano);
		pseudoRandom.ints(10, 0, 10).forEach(i -> System.out.println(i));
	}
}
