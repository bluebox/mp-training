package Threads;

public class Main {

	public static void main(String[] args) {
		ShoeWareHouse warehouse=new ShoeWareHouse();
		Producer producer=new Producer(warehouse);
		Consumer consumer1=new Consumer(warehouse,5);
		Consumer consumer2=new Consumer(warehouse,5);
		
		producer.start();
		consumer1.start();
		consumer2.start();
	}
	
	

}
