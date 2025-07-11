package day11;

public class StoreMain {

	public static void main(String[] args) {
		ShoeWarehouse warehouse = new ShoeWarehouse();

		Thread producerThread = new Thread(new Producer(warehouse));
		Thread consumerThread1 = new Thread(new Consumer(warehouse, "Consumer-1"));
		Thread consumerThread2 = new Thread(new Consumer(warehouse, "Consumer-2"));
		producerThread.start();
		consumerThread1.start();
		consumerThread2.start();

		producerThread.start();
		consumerThread1.start();
		consumerThread2.start();
	}
}
