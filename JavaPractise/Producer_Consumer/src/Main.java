public class Main {
	public static void main(String[] args) {
		StoreWareHouse sw=new StoreWareHouse();
		Producer p=new Producer(sw);
		Consumer c=new Consumer(sw);
		p.start();
		c.start();
		p.setName("Producer");
		c.setName("Consumer");
	}

}
