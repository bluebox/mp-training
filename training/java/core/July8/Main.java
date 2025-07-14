package July8;

import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println((Integer.MIN_VALUE));
		System.out.println(Math.abs(Integer.MIN_VALUE));
		//System.out.println(Math.absExact(Integer.MIN_VALUE)); //gives error
		System.out.println(Math.abs((long)(Integer.MIN_VALUE)));
		
		System.out.println(Math.min(10.0000002, 10.001));
		System.out.println(Math.min(10.0000002f, 10.001f));
		System.out.println(Math.min(10.0000002, 10.001f));
		System.out.println(Math.min(10.0000002f, 10.001));
		
		System.out.printf("%1$d = %1$c%n", (int)(Math.random()*26)+65);
		Random r = new Random();
		System.out.printf("%1$d = %1$c%n", r.nextInt(65, 91));
		System.out.printf("%1$d%n", r.nextInt());
	}
}

