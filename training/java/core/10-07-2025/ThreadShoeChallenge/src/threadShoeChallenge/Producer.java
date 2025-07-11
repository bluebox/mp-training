package threadShoeChallenge;

public class Producer extends Thread {
private final ShoeWareHouse wh;

public Producer(ShoeWareHouse wh) {
	super();
	this.wh = wh;
}
public void run()
{
	for(int i=1;i<=5;i++)
	{
		Order order=new Order(i,"ShoeType"+i,i*2);
		wh.receiveOrder(order);
		try{
			Thread.sleep(100);
			
		}
		catch(InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}
}
}}


