package myRandamization;

import java.util.Random;

public class MyRandomClass {
	public static void main(String[] args) {
		Random random = new Random();
		int myRandomVariable = random.nextInt();
		System.out.println("My random Variable is " + myRandomVariable);
		myRandomVariable = random.nextInt(50);
		System.out.println("My random Variable is " + myRandomVariable);
		myRandomVariable = (int) (Math.random() * 26 + 65);
		System.out.println("My random Variable is " + myRandomVariable);
		myRandomVariable = 5;
		System.out.println("My random Variable is " + myRandomVariable);
		System.out.println("power using math " + Math.pow(myRandomVariable, 3));
		Integer myInt = Integer.MAX_VALUE - 5;
		for (int i = 0; i < 10; i++) {
			Math.incrementExact(myInt);
			System.out.println(myInt);
		}
		System.out.println("My random Variable is with abs " + Math.abs(Integer.MIN_VALUE + 10) + " and without abs "
				+ (Integer.MIN_VALUE + 10));
		
	}

}
