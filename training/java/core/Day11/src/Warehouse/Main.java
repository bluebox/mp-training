package Warehouse;

public class Main {
    public static void main(String[] args) {
        WareHouse wareHouse = new WareHouse();

        Thread producer = new Thread(() -> {
            int orderId = 1;

            while (true) {
                try {
                    String shoeType = WareHouse.PRODUCT_LIST[orderId % WareHouse.PRODUCT_LIST.length];
                    int quantity = (orderId % 6) - 1;

                    Order order = new Order(orderId, shoeType, quantity);

                    try {
                        wareHouse.receiveOrder(order);
                    } catch (CustomException ce) {
                        System.out.println("Order Error: " + ce.getMessage());
                    }

                    orderId++;
                    Thread.sleep(800);

                } catch (InterruptedException e) {
                    System.out.println("Producer interrupted.");
                    break;
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    Order order = wareHouse.fulfillOrder();
                    System.out.printf("Fulfilled Order: #%d | Shoe: %s | Qty: %d%n",
                            order.orderId(), order.shoeType(), order.quantity());

                    Thread.sleep(1200);

                } catch (InterruptedException e) {
                    System.out.println("Consumer interrupted.");
                    break;
                }
            }
        });

        producer.start();
        consumer.start();
    }
}