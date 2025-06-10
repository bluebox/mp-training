package june6_multithreading;
import java.util.*;

record Order(long id, String productName, int quantity) {
}
public class Warehouse {

    private final List<Order> pendingOrders = new ArrayList<>();
    public static final String[] PRODUCTS = {
            "Sneakers", "Loafers", "Formals", "Flip Flops", "Sports Shoes"
    };

    public synchronized void addOrder(Order order) {
        while (pendingOrders.size() >= 20) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        pendingOrders.add(order);
        System.out.println("Order Placed: " + order);
        notifyAll();
    }

    public synchronized void processOrder() {
        while (pendingOrders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        Order order = pendingOrders.remove(0);
        System.out.println(Thread.currentThread().getName() + " Shipped: " + order);
        notifyAll();
    }
}
