package threadShoeChallenge;

public class ThreadShoeMain {
public static void main(String []args)
{
	ShoeWareHouse wh=new ShoeWareHouse();
	Producer prdcr=new Producer(wh);
	Consumer c1=new Consumer(wh,5);
	Consumer c2=new Consumer(wh,5);
	prdcr.start();
	c1.start();
	c2.start();
	
}
}
