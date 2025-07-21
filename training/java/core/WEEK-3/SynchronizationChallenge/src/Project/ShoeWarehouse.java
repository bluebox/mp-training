package Project;

import java.util.LinkedList;
import java.util.List;

public class ShoeWarehouse {
    public static final List<String> productList = List.of("Sneakers", "Boots", "Sandals", "Loafers");

    private final List<Order> orders = new LinkedList<>();
    private final int MAX_CAPACITY = 5;

    public synchronized void receiveOrder(Order order) throws InterruptedException {
        while (orders.size() >= MAX_CAPACITY) {
            wait();
        }
        orders.add(order);
        System.out.println("Order received: " + order);
        notifyAll(); // Notify consumers
    }

    public synchronized Order fulfillOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        Order order = orders.remove(0); // FIFO
        System.out.println("Order fulfilled: " + order);
        notifyAll(); // Notify producers
        return order;
    }
}

