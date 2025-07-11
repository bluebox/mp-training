import java.util.LinkedList;
import java.util.List;

public class StoreWareHouse {
		List<Order> list = new LinkedList<>();
		public synchronized void receiveOrder(Order product)
		{
			if(list.size()>=5) {
				System.out.println(Thread.currentThread().getName()+"is waiting");
				try {
					wait();
				}
				catch(Exception e) {}
			}
			list.add(product);
			System.out.println("Product addded successfully:"+product.getName());
			notifyAll();
		}
		public synchronized void fulFillOrder()
		{
			if(list.size()<=0) {
				System.out.println(Thread.currentThread().getName()+"is waiting");
				try {
					wait();
				}
				catch(Exception e) {}
			}
			System.out.println("Product consumed  successfully:"+list.remove(0).getName());
			notifyAll();
		}
}
