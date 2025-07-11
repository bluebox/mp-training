package threadShoeChallenge;

public class Consumer extends Thread {
 private final ShoeWareHouse wh;
 int orderToProcess;
public Consumer(ShoeWareHouse wh, int orderToProcess) {
	super();
	this.wh = wh;
	this.orderToProcess = orderToProcess;
}
 public void run()
 {
	 for(int i=0;i<orderToProcess;i++)
	 {
		 wh.fulfilOrder();
		 try {
			 Thread.sleep(150);
		 }
		 catch(InterruptedException e)
		 {
			 Thread.currentThread().interrupt();
		 }
	 }
 }
}
