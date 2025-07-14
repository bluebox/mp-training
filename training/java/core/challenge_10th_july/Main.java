package challenge_10th_july;

public class Main {
    public static void main(String[] args) {
        ShoeWarehouse warehouse = new ShoeWarehouse();

        Producer producer = new Producer(warehouse);
        Consumer consumer1 = new Consumer(warehouse, "Consumer-1");
        Consumer consumer2 = new Consumer(warehouse, "Consumer-2");

        producer.start();
        consumer1.start();
        consumer2.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All orders processed.");
    }
}
