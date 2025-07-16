package corejava.july10_shoeWarehouse;

import java.util.Random;

record Order (long OrderId, String item, int Qty) {};

public class WareHouseMain {
	private static final Random random = new Random();
    public static void main (String[] args) {
    	
    	Warehouse warehouse = new Warehouse();
	    Thread producerThread = new Thread(() ->  {
	            for (int j = 0; j < 10; j++ ) {
	                warehouse.receivedOrder(
	                		new Order( random.nextLong(100 ,999),Warehouse.PRODUCT_LIST[random.nextInt(0,5)], random.nextInt(1,5)));
	            }
	        });
	    producerThread.start();
	    
	    Thread consumerThread1 = new Thread(() -> { 
	    	for (int j = 0 ; j < 5; j++) { 
	    		warehouse.fulfillOrder();
	    	} 
	    	});
		consumerThread1.start();
		
		Thread consumerThread2 = new Thread(() -> { 
	    	for (int j = 0 ; j < 5; j++) { 
	    		warehouse.fulfillOrder();
	    	} 
	    	});
		consumerThread2.start();
	}   	
}
