package day11;

import java.util.LinkedList;
import java.util.List;

public class ShoeWhareHouse {
	private final List<Order> orders = new LinkedList<>();
    private final int MAX_CAPACITY = 5;

    public synchronized void receiveOrder(Order order) {
        while (orders.size() >= MAX_CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Receive interrupted");
                return;
            }
        }
        orders.add(order);
        System.out.println("Received Order #" + order.getOrderId());
        notifyAll();
    }

    public synchronized Order fulfillOrder() {
        while (orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Fulfill interrupted");
                return null;
            }
        }
        Order fulfilled = orders.remove(0);
        System.out.println("Fulfilled Order #" + fulfilled.getOrderId());
        notifyAll();
        return fulfilled;
}
 
}


class Producer extends Thread {
    private final ShoeWhareHouse warehouse;

    public Producer(ShoeWhareHouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            Order order = new Order(i);
            warehouse.receiveOrder(order);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted.");
                break;
            }
        }
    }
}

 
class Consumer extends Thread {
	    private final ShoeWhareHouse warehouse;

	    public Consumer(ShoeWhareHouse warehouse, String name) {
	        super(name);
	        this.warehouse = warehouse;
	    }

	    @Override
	    public void run() {
	        for (int i = 0; i < 5; i++) {
	            Order order = warehouse.fulfillOrder();
	            if (order != null) {
	                System.out.println(getName() + " processed Order #" + order.getOrderId());
	            }
	            try {
	                Thread.sleep(300);
	            } catch (InterruptedException e) {
	                System.out.println(getName() + " interrupted.");
	                break;
	            }
	        }
	    }
	}
 