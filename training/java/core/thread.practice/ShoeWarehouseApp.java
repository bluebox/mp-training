import java.util.concurrent.*;

class ShoeOrder implements Runnable {
    private final int orderId;

    public ShoeOrder(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        System.out.println("Processing Order #" + orderId + " by " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000); // simulate time taken to process
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Completed Order #" + orderId + " by " + Thread.currentThread().getName());
    }
}

class ShoeWarehouse {
    private final ExecutorService fulfillmentExecutor;

    public ShoeWarehouse(int poolSize) {
        // Fulfill orders using FixedThreadPool
        this.fulfillmentExecutor = Executors.newFixedThreadPool(poolSize);
    }

    public void fulfillOrder(ShoeOrder order) {
        fulfillmentExecutor.submit(order);
    }

    public void shutdown() {
        fulfillmentExecutor.shutdown();
        try {
            if (!fulfillmentExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                fulfillmentExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            fulfillmentExecutor.shutdownNow();
        }
    }
}

public class ShoeWarehouseApp {
    public static void main(String[] args) {
        ExecutorService orderSenderExecutor = Executors.newSingleThreadExecutor();
        ShoeWarehouse warehouse = new ShoeWarehouse(3); // FixedThreadPool of size 3

        for (int i = 1; i <= 15; i++) {
            int finalI = i;
            orderSenderExecutor.submit(() -> {
                ShoeOrder order = new ShoeOrder(finalI);
                warehouse.fulfillOrder(order);
            });
        }

        orderSenderExecutor.shutdown();
        try {
            if (!orderSenderExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                orderSenderExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            orderSenderExecutor.shutdownNow();
        }

        warehouse.shutdown();
        System.out.println("All orders processed.");
    }
}
