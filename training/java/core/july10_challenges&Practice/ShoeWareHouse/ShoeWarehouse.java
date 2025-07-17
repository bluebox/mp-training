package shoeWareHouse;

import java.util.*;

public class ShoeWarehouse {
    public static final List<String> productList = List.of("Sneakers", "Boots", "Sandals", "Loafers");
    private final Queue<Order> orderQueue = new LinkedList<>();
    private final int MAX_CAPACITY = 5;

    public synchronized void receiveOrder(Order order) {
        while (orderQueue.size() >= MAX_CAPACITY) {
            try {
                System.out.println("Warehouse full. Producer waiting...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        orderQueue.offer(order);
        System.out.println("Order Received: " + order);
        notifyAll();
    }

    public synchronized Order fulfillOrder() {
        while (orderQueue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting for orders...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Order order = orderQueue.poll();
        System.out.println(Thread.currentThread().getName() + " fulfilled: " + order);
        notifyAll();
        return order;
    }
}
