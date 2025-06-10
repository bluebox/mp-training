package june6_multithreading;
import java.util.*;

public class Main {

    private static final Random rand = new Random();

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        // Producer thread
        Thread orderProducer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                long orderId = rand.nextLong(1000000, 9999999);
                String product = Warehouse.PRODUCTS[rand.nextInt(Warehouse.PRODUCTS.length)];
                int quantity = rand.nextInt(1, 4);
                Order order = new Order(orderId, product, quantity);
                warehouse.addOrder(order);
            }
        }, "Producer");

        orderProducer.start();

        // Consumer threads
        for (int i = 1; i <= 2; i++) {
            Thread orderConsumer = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    warehouse.processOrder();
                }
            }, "Consumer-" + i);
            orderConsumer.start();
        }
    }
}
