package com.dailybasics;

public class ThreadStateDemo {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				System.out.println("In Thread : " + i);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Thread Interrupted ");
			}
		});
		System.out.println("State : " + t.getState());
		t.start();
		System.out.println("State : " + t.getState());
		Thread.sleep(500);
		System.out.println("State : " + t.getState());
		t.join();
		System.out.println("State : " + t.getState());
	}
}
