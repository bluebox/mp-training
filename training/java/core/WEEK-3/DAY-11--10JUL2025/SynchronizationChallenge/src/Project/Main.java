package Project;

public class Main {
    public static void main(String[] args) {
        ShoeWarehouse warehouse = new ShoeWarehouse();

        Thread producerThread = new Thread(new Producer(warehouse));
        Thread consumerThread1 = new Thread(new Consumer(warehouse));
        Thread consumerThread2 = new Thread(new Consumer(warehouse));

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();

        try {
            producerThread.join();
            consumerThread1.join();
            consumerThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All orders processed.");
    }
}

