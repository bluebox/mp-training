package shoeWareHouse;

public class Main {
    public static void main(String[] args) {
        ShoeWarehouse warehouse = new ShoeWarehouse();

        Thread producer = new Thread(new Producer(warehouse), "Producer");
        Thread consumer1 = new Thread(new Consumer(warehouse), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(warehouse), "Consumer-2");

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
