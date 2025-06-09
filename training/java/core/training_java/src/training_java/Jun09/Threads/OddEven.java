package Threads;

import java.util.Random;

public class OddEven {
	public static void main(String[] args) throws InterruptedException {
		Thread odd=new Odd();
		Runnable runnable =new Even();
		Thread even=new Thread(runnable);
		Thread all=new Thread() {
			public void run() {
				new Random().ints(5, 90, 100).forEach(i->System.out.println("All Thread : "+i));
			}
		};
		odd.start();
		even.start();
		odd.interrupt();
		all.start();
		
	}

}
