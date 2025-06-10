package june9_multithreading;

import java.util.Random;
import java.util.concurrent.*;

record Order(long orderId, String item, int qty) {
}

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {

        ShoeWarehouse warehouse = new ShoeWarehouse();

        ExecutorService orderingService = Executors.newSingleThreadExecutor();

        // Creating and submitting order tasks
        for (int i = 0; i < 15; i++) {
            Callable<Object> task = () -> {
                Order order = generateOrder();
                try {
                    // Random delay before placing the order
                    int delay = 300 + random.nextInt(1700);
                    TimeUnit.MILLISECONDS.sleep(delay);
                    warehouse.receiveOrder(order);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // safer than RuntimeException
                }
				return order;
            };

            try {
                // Random delay before submitting the task
                Thread.sleep(200 + random.nextInt(800));
                orderingService.submit(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Shutdown procedure
        orderingService.shutdown();
        try {
            if (!orderingService.awaitTermination(6, TimeUnit.SECONDS)) {
                orderingService.shutdownNow();
            }
        } catch (InterruptedException e) {
            orderingService.shutdownNow();
        }

        warehouse.shutDown();
    }

    private static Order generateOrder() {
        long id = random.nextLong(1_000_000, 9_999_999);
        String item = ShoeWarehouse.PRODUCT_LIST[random.nextInt(ShoeWarehouse.PRODUCT_LIST.length)];
        int quantity = random.nextInt(1, 4);
        return new Order(id, item, quantity);
    }
}
