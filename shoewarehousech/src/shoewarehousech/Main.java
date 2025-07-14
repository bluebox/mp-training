package shoewarehousech;
import java.util.*;
record Order(long orderid,String item,int qty) {
	
};

public class Main {
	private static final Random random=new Random();
	public static void main(String[] args) {
		shoewarehouse warehouse=new shoewarehouse();
		Thread ProducerThread=new Thread(()->{
			for(int j=0;j<10;j++) {
			warehouse.receiveOrder(new Order(
					random.nextLong(100000,9999999),
					shoewarehouse.product_list[random.nextInt(0,5)],
					random.nextInt(1,4)));
		}
	});
		ProducerThread.start();
		for(int i=0;i<2;i++) {
			Thread consumerThread=new Thread(()->{
			for(int j=0;j<5;j++) {
				Order item=warehouse.fulfillOrder();
			}
			});
			consumerThread.start();
		}
					
		}
	}


