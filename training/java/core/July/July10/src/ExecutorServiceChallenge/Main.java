package ExecutorServiceChallenge;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static Random random = new Random();
	public static void main(String[] args) {
		
		
		WareHouse warehouse = new WareHouse();
		
		ExecutorService orderingService = Executors.newCachedThreadPool();
		
		 Callable<Order> ordering =  ()->{
			 Order newOrder = generateOrder();
			 try {
				 Thread.sleep(random.nextInt(200,2000));
				 warehouse.receiveOrder(newOrder);
			 }catch (InterruptedException e) {
				 e.printStackTrace();
			 }
			 return newOrder;
			};
	
		try {
			for( int i=0; i<15; i++){
				Thread.sleep(random.nextInt(200,2000));
				orderingService.submit(()-> warehouse.receiveOrder(generateOrder()));
			}
			}catch(InterruptedException e) {
				throw new RuntimeException(e);
			}
		orderingService.shutdown();
		}
	
	private static Order generateOrder() {
			return new Order(
					random.nextLong(100000,999999999),
					WareHouse.productItems[random.nextInt(0,4)],
					random.nextInt(1,8));
	}
}
