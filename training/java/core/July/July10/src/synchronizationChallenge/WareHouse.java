package synchronizationChallenge;

import java.util.ArrayList;
import java.util.List;

public class WareHouse {
	
	public static final String[] productItems = {
			"Shoes", "Boots", "Slippers", "Sandals"    
			};
	
	private List<Order> productList;

	public WareHouse( ) {
		this.productList = new ArrayList<>();
	}
	
	public synchronized void receiveOrder(Order item) {
		while(productList.size() > 20) {
			try {
				wait();
			}catch(InterruptedException e) {
				System.out.println("Error occured while reciving Order");
			}
		}
		productList.add(item);
		System.out.println("Order Recived "+item);
		notifyAll();
	}
	
	public synchronized Order fulfillOrder( ) {
		
		while(productList.isEmpty()) {
			try {
				wait();
			}catch(InterruptedException e) {
				System.out.println("Error occured while fulfilling Order");
			}
		}
		
		Order item = productList.remove(0);
		System.out.println(Thread.currentThread().getName()+" fulfilled "+item);
		notifyAll();
		return item;
	}
}
