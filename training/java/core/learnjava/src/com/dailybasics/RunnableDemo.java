package com.dailybasics;

class MyRunnable implements Runnable {
	public void run() {
		for (char i = 'A'; i <= 'E'; i++) {
			System.out.println("Thread 2 : " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Thread Interrupted");
			}
		}
	}
}

public class RunnableDemo {
	public static void main(String[] args) {
		MyRunnable runnable = new MyRunnable();
		Thread t2 = new Thread(runnable);
		t2.start();
		System.out.println("Main Thread is Running !!!");
	}
}
