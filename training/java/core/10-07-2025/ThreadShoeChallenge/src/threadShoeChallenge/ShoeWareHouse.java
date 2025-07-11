package threadShoeChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class ShoeWareHouse {
 private final Queue<Order>orders=new LinkedList<>();

 public synchronized  void receiveOrder(Order order)
 {
	 while(orders.size()>=5)
	 {
		 try {
			 wait();
		 }
		 catch(InterruptedException e)
		 {
			 Thread.currentThread().interrupt();
		 }
	 }
	 orders.add(order);
	 System.out.println("recieved order : "+order);
	 notifyAll();
 }
 public synchronized Order fulfilOrder()
 {
	 while(orders.isEmpty())
	 {
		 try {
			 wait();
		 }
		 catch (InterruptedException e)
		 {
			 Thread.currentThread().interrupt();
		 }
	 }
	 Order order=orders.poll();
	 System.out.println("order given: "+order);
	 notifyAll();
	 return order;
 }
 
}
