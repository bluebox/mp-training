package day11;

public class ProducerConsumer {
	public static void main(String[] args) {
		ShoeWhareHouse warehouse = new ShoeWhareHouse();

		Producer producer = new Producer(warehouse);
		Consumer consumer1 = new Consumer(warehouse, "Consumer-A");
		Consumer consumer2 = new Consumer(warehouse, "Consumer-B");

		producer.start();
		consumer1.start();
		consumer2.start();

		try {
			producer.join();
			consumer1.join();
			consumer2.join();
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("All orders fulfilled!");
		    
	}
}
