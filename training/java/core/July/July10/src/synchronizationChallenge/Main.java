package synchronizationChallenge;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		
		Random random = new Random();
		WareHouse warehouse = new WareHouse();
		
		Thread producerThread = new Thread( ()->{
					for(int i=0;i< 10;i++) {
						warehouse.receiveOrder(new Order(
								random.nextLong(100000,999999999),
								WareHouse.productItems[random.nextInt(0,4)],
								random.nextInt(1,8)));
					}
				});
	
		producerThread.start();
		
		for( int i=0; i<2; i++) {
			Thread consumerThread = new Thread( ()-> {
				for(int j=0; j<5; j++) {
					 warehouse.fulfillOrder();
				}
			});
			
			consumerThread.start();		
		}
	}
}
