package com.dailybasics;

public class InterruptDemo {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println("Working");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("its Interrupted");
					Thread.currentThread().interrupt();
					break;
				}
			}
		});
		t.start();
		Thread.sleep(2000);
		t.interrupt();
	}

}
