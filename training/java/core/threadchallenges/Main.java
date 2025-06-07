package threadchallenges;

import java.util.ArrayList;
import java.util.List;

class Order {
    private final int orderId;
    private final String shoeType;
    private final int quantity;

    public Order(int orderId, String shoeType, int quantity) {
        this.orderId = orderId;
        this.shoeType = shoeType;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getShoeType() {
        return shoeType;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order{id=" + orderId + ", type=" + shoeType + ", qty=" + quantity + "}";
    }
}

class ShoeWarehouse {
    public static final List<String> productList = List.of("Sneakers", "Boots", "Sandals", "Loafers", "Slippers");
    private final List<Order> orders = new ArrayList<>();
    private final int MAX_CAPACITY = 5;

    public synchronized void receiveOrder(Order order) throws InterruptedException {
        while (orders.size() >= MAX_CAPACITY) {
            wait();
        }
        orders.add(order);
        System.out.println("Received: " + order);
        notifyAll();
    }

    public synchronized Order fulfillOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        Order order = orders.remove(0); // FIFO
        System.out.println("Fulfilled: " + order);
        notifyAll();
        return order;
    }
}

class Producer extends Thread {
    private final ShoeWarehouse warehouse;

    public Producer(ShoeWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            String type = ShoeWarehouse.productList.get(i % ShoeWarehouse.productList.size());
            Order order = new Order(i, type, (i % 3 + 1) * 10);
            try {
                warehouse.receiveOrder(order);
                Thread.sleep(100); // simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private final ShoeWarehouse warehouse;
    private final int numberOfOrdersToProcess;

    public Consumer(ShoeWarehouse warehouse, int numberOfOrdersToProcess) {
        this.warehouse = warehouse;
        this.numberOfOrdersToProcess = numberOfOrdersToProcess;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfOrdersToProcess; i++) {
            try {
                warehouse.fulfillOrder();
                Thread.sleep(150); // simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ShoeWarehouse warehouse = new ShoeWarehouse();

        Producer producer = new Producer(warehouse);
        Consumer consumer1 = new Consumer(warehouse, 5);
        Consumer consumer2 = new Consumer(warehouse, 5);

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

        System.out.println("All orders have been processed.");
    }
}

