package com.dailybasics;

class NoSync {
	int count = 0;

	void increament() {
		count++;
	}
}

class Sync {
	int count = 0;

	synchronized void increment() {
		count++;
	}
}

public class WhySynchronous {
	public static void main(String[] args) throws InterruptedException {
		NoSync ns = new NoSync();
		Sync s = new Sync();
		Runnable run = () -> {
			for (int i = 0; i < 1000; i++) {
				ns.increament();
			}
		};
		Runnable runs = () -> {
			for (int i = 0; i < 1000; i++) {
				s.increment();
			}
		};
		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		Thread t3 = new Thread(runs);
		Thread t4 = new Thread(runs);

		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("NoSync count : " + ns.count);
		System.out.println("-----------------------------------------");
		t3.start();
		t4.start();
		t3.join();
		t4.join();
		System.out.println("Sync count : " + s.count);
	}

}
