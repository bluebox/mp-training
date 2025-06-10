package Threads;


public class Producer extends Thread{
	private ShoeWareHouse warehouse;
	public Producer(ShoeWareHouse warehouse) {
		this.warehouse=warehouse;
	}
	@Override
	public void run() {
		try {
			for(int i=1;i<=10;i++) {
				String order="Order-"+i;
				warehouse.receiveOrder(order);
				Thread.sleep(300);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}