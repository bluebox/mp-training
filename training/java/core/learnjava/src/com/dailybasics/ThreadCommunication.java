package com.dailybasics;

class Shop {
	int item = 0;

	synchronized void producer() throws InterruptedException {
		if (item > 0) {
			wait();// waits
		}
		item++;
		System.out.println("Item added :" + item);
		notify();// notifies waiting threads
	}

	synchronized void consumer() throws InterruptedException {
		if (item == 0) {
			wait();// waits
		}
		item--;
		System.out.println("Item added :" + item);
		notify();// notifies waiting threads
	}
}

public class ThreadCommunication {
	public static void main(String[] args) {
		Shop shop = new Shop();
		Runnable runProducer = () -> {
			try {
				for (int i = 0; i < 3; i++) {
					shop.producer();
					Thread.sleep(500);

				}
			} catch (InterruptedException e) {
				System.out.println("Producer Interrupted .");
			}
		};
		Runnable runConsumer = () -> {
			try {
				for (int i = 0; i < 3; i++) {
					shop.consumer();
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				System.out.println("Consumer interrupted .");
			}
		};
		Thread t1 = new Thread(runConsumer);
		Thread t2 = new Thread(runProducer);
		t2.start();
		t1.start();
	}

}
