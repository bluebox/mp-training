package ExecutorServiceChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WareHouse {
	
	private final  ExecutorService fulfillmentService;
	
	public static final String[] productItems = {
			"Shoes", "Boots", "Slippers", "Sandals"    
			};
	
	private List<Order> productList;

	public WareHouse( ) {
		this.productList = new ArrayList<>();
		fulfillmentService = Executors.newFixedThreadPool(4);
	}
	
	public void shutDown() {
		fulfillmentService.shutdown();
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
		fulfillmentService.submit(this::fulfillOrder);
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
