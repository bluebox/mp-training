public class Producer extends Thread{
	public StoreWareHouse sw;
	Producer(StoreWareHouse sw){
		this.sw=sw;
	}
	public void run() {
		for(int i=0;i<=10;i++) {
			Order o=new Order(i,"A"+i,i);
			sw.receiveOrder(o);
		}
	}
}
