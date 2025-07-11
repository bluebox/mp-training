package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoeWarehouse {
	public static List<String> productList = Arrays.asList("Sports Shoe", "Sandal", "Slipper");
	private List<Order> orders = new ArrayList<>();
	private static final int CAPACITY = 5;

	public synchronized void receiveOrder(Order order) {
		while (orders.size() >= CAPACITY) {
			try {
				wait();
			} catch (Exception e) {
				Thread.currentThread().interrupt();
                System.err.println("Producer thread interrupted");
			}
		}
		orders.add(order);
		System.out.println("Warehouse received: " + order);
		notifyAll();
	}

	public synchronized Order fulfillOrder() {
		while (orders.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
                System.err.println("Consumer thread interrupted");
			}
		}
		Order order = ((ArrayList<Order>) orders).remove(0);
		notifyAll();
		return order;
	}
}
