package Warehouse;

import java.util.LinkedList;
import java.util.Queue;

public class WareHouse {
    public static final String[] PRODUCT_LIST = {
        "Sneakers", "Boots", "Sandals", "Loafers", "Running Shoes"
    };

    private final Queue<Order> orders = new LinkedList<>();
    private final int MAX_CAPACITY = 5;

    public synchronized void receiveOrder(Order order) throws InterruptedException, CustomException {
        if (order.quantity() <= 0) {
            throw new CustomException("Quantity can't be 0 or negative.");
        }

        while (orders.size() >= MAX_CAPACITY) {
            System.out.println("Warehouse full. Producer waiting...");
            wait();
        }

        orders.add(order);
        System.out.printf("Received Order: #%d | Shoe: %s | Qty: %d%n",
                order.orderId(), order.shoeType(), order.quantity());
        notifyAll();
    }

    public synchronized Order fulfillOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            System.out.println("Warehouse empty. Consumer waiting...");
            wait();
        }

        Order order = orders.poll();
        notifyAll();
        return order;
    }
}