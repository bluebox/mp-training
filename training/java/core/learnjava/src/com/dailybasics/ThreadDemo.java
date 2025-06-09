package com.dailybasics;

class MyThread extends Thread {
	public void run() {// run() method of thread
		for (int i = 0; i < 5; i++) {
			System.out.println("Thread 1 : " + i);
			try {
				Thread.sleep(500);// Waits 0.5 secs
			} catch (InterruptedException e) {
				System.out.println("Thread is interrupted");
			}
		}
	}
}

public class ThreadDemo {
	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		t1.start();
		System.out.println("Main Thread is running !!!");
	}

}
