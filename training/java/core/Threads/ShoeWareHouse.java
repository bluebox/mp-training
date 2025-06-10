package Threads;

import java.util.ArrayList;
import java.util.List;

public class ShoeWareHouse {
	private List<String> productsList = new ArrayList<>();
	private final List<String> orders = new ArrayList<>();

	private final int MAX_CAPACITY = 5;

	public synchronized void receiveOrder(String order) throws InterruptedException {
		
		while(orders.size()>=MAX_CAPACITY) {
			wait();
		}
		orders.add(order);
		System.out.println("Order received: "+order);
		notifyAll();
	}
	
	public synchronized String fullFillOrder() throws InterruptedException {
		while(orders.isEmpty()) {
			wait();
		}
		String order=orders.remove(0);
		System.out.println(order);
		notifyAll();
		return order;
	}
}
