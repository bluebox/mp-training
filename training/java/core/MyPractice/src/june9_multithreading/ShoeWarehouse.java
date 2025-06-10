package june9_multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoeWarehouse {

    private final List<Order> shippingItems;
    private final ExecutorService fulfillmentService;

    public static final String[] PRODUCT_LIST = {
            "Running Shoes", "Sandals", "Boots", "Slippers", "High Tops"
    };

    public ShoeWarehouse() {
        shippingItems = new ArrayList<>();
        fulfillmentService = Executors.newFixedThreadPool(3);
    }

    public void shutDown() {
        fulfillmentService.shutdown();
    }

    public synchronized void receiveOrder(Order item) {
        // Wait until there is space in the list
        while (shippingItems.size() > 20) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        shippingItems.add(item);
        System.out.println(Thread.currentThread().getName() + " Incoming: " + item);

        // Assign fulfillment task to thread pool
        fulfillmentService.execute(() -> {
            fulfillOrder();
        });

        notifyAll(); // Wake up any waiting threads
    }

    public synchronized Order fulfillOrder() {
        // Wait until there is at least one item
        while (shippingItems.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        Order currentOrder = shippingItems.remove(0);
        System.out.println(Thread.currentThread().getName() + " Fulfilled: " + currentOrder);
        notifyAll();
        return currentOrder;
    }

    public synchronized void stopProcessing() {
        int remaining = shippingItems.size();
        System.out.println("Processing complete. Orders left: " + remaining);
    }
}
