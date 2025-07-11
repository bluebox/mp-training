package day11;

import java.util.ArrayList;
import java.util.List;

public class Sync {
	private static List<Integer> arr = new ArrayList<>();
	private static Runnable consumer = new Runnable() {

		@Override
		public void run() {
			while (true) {
				synchronized (arr) {
					while (arr.isEmpty()) {
						try {
							System.out.println("arr is empty, consumer is waiting");
							arr.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					arr.remove(0);
					System.out.println("10 added and notified");
					arr.notify();
					try {
                        Thread.sleep(2000); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
				}
			}
		}
	};
	private static Runnable producer = new Runnable() {

		@Override
		public void run() {
			while (true) {
				synchronized (arr) {
					while (arr.size() == 1) {
						try {
							System.out.println("arr is full, producer is waiting");
							arr.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					arr.add(10);
					System.out.println("10 removed and notified");
					arr.notifyAll();
					try {
                        Thread.sleep(2000); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
				}
			}
		}
	};

	public static void main(String[] args) {
		Thread p = new Thread(producer);
		Thread c = new Thread(consumer);
		p.start();
		c.start();
		System.out.println("Main thread exiting");

	}
}
