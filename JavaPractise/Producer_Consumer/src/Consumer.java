public class Consumer extends Thread{
	public StoreWareHouse sw;
	Consumer(StoreWareHouse sw){
		this.sw=sw;
	}
	public void run() {
		for(int i=0;i<=10;i++) {
			sw.fulFillOrder();
		}
	}
}
