package Threads;

public class Consumer extends Thread{
	private ShoeWareHouse warehouse;
	private int orders;
	public Consumer(ShoeWareHouse warehouse,int orders) {
		this.warehouse=warehouse;
		this.orders=orders;
	}
	public void run() {
		try {
			for(int i=0;i<orders;i++) {
				warehouse.fullFillOrder();
				Thread.sleep(300);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
