package Math;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		

       System.out.println(Math.abs(-50));
       System.out.println(Math.abs(Integer.MIN_VALUE));
       System.out.println(Math.abs((long) Integer.MIN_VALUE));

       System.out.println("Max = " + Math.max(10, -10));
       System.out.println("Min = " + Math.min(10.0000002f, 10.001));
       System.out.println("Min = " + Math.min(10.0000002, 10.001f));

       System.out.println("Round Down = " + Math.round(10.2));
       System.out.println("Round Up = " + Math.round(10.8));
       System.out.println("Round  = " + Math.round(10.5));

       System.out.println("Floor = " + Math.floor(10.8));
       System.out.println("Ceil = " + Math.ceil(10.2));

       System.out.println("Square root of 100 = " + Math.sqrt(100));
       System.out.println("2 to the third power = " + Math.pow(2, 3));
       System.out.println("10 to the fifth power = " + Math.pow(10, 5));

       for (int i = 0; i < 10; i++) {
           System.out.println(Math.random());
       }
       
       for (int i = 0; i < 10; i++) {
           System.out.println((int)Math.random());
       }
       
       
       for (int i = 0; i < 10; i++) {
           System.out.printf("%1$d = %1$c%n", (int) (Math.random()*26) + 65);
       }
       
       Random r = new Random();
       for (int i = 0; i < 10; i++) {
           System.out.printf("%1$d = %1$c%n", r.nextInt(91));
       }

	}

}
