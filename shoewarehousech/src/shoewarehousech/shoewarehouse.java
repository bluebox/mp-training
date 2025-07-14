package shoewarehousech;
import java.lang.Thread;
import java.util.*;

public class shoewarehouse {
	private List<Order> shipping_items;
	public final static String[] product_list={"Runnning shoes","Sandals","boots","tops","pants"};
	public shoewarehouse() {
		this.shipping_items=new ArrayList<>();
	}
	
	public synchronized void receiveOrder(Order item) {
		while(shipping_items.size()>20) {
			try {
				wait();
			}catch(InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		shipping_items.add(item);
		System.out.println("icoming..."+item);
		notifyAll();
		
		
		
	}
	public synchronized Order fulfillOrder() {
		while(shipping_items.isEmpty()) {
			try {
				wait();
			}catch(InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
			Order item=shipping_items.remove(0);
			System.out.println(Thread.currentThread().getName()+"fulfilled item"+item);
			notifyAll();
			return item;
			
		}
		
	}
	
	
	


