package Day11_10_07_WareHouseChallenge;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final int TOTAL_ORDERS = 10;
    public static void main(String[] args) {
        WareHouse warehouse = new WareHouse();
        AtomicInteger fulfilledOrders = new AtomicInteger(0);
        Thread producerThread = new Thread(() -> {
            int orderId = 1;
            while (orderId <= TOTAL_ORDERS) {
                try {
                    String shoeType = WareHouse.PRODUCT_LIST[(int) (Math.random() * WareHouse.PRODUCT_LIST.length)];
                    int quantity = 1 + (int) (Math.random() * 5);
                    Order order = new Order(orderId, shoeType, quantity);
                    warehouse.receiveOrder(order);
                    orderId++;
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

            System.out.println("Producer finished producing " + TOTAL_ORDERS + " orders.");
        });

        Thread consumerThread = new Thread(() -> {
            while (true) {
                try {
                    if (fulfilledOrders.get() >= TOTAL_ORDERS) {
                        break;
                    }

                    Order order = warehouse.fulfillOrder();

                    System.out.printf("Fulfilled Order: #%d | Shoe: %s | Qty: %d%n",
                            order.orderId(), order.shoeType(), order.quantity());

                    fulfilledOrders.incrementAndGet();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

            System.out.println(" Consumer finished fulfilling " + TOTAL_ORDERS + " orders.");
        });

        producerThread.start();
        consumerThread.start();
    }
}
