
public class SynchronizationExample {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		BrickDiary b1=new BrickDiary();
		Runnable r1=()->
		{
			for(int i=0;i<15000;i+=50)
			{
				b1.brickCounts();
			}
		};
		Runnable r2=()->
		{
			for(int i=0;i<10000;i+=50)
			{
				b1.brickCounts();
			}
		};
		Runnable r3=()->
		{
			for(int i=0;i<5000;i+=50)
			{
				b1.brickCounts();
			}
		};
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		Thread t3=new Thread(r3);
//		t1.start();
//		t2.start();
//		t3.start();
		System.out.println("First Main thread runs "+b1.brickCount);
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		System.out.println("When the main thread runs after all threads execution "+b1.brickCount);
		System.out.println("after synchronized it gives correct count "+b1.brickCount);
		System.out.println("without synchronization it gives wrong count count "+b1.brickcount1);
	}
}

class BrickDiary
{
	int brickCount=0;
	int brickcount1=0;
//	public synchronized void brickCounts()
//	{
//		brickCount+=50;
//	}
	public void brickCounts()
	{
		synchronized(this)
		{
	      brickCount+=50;
		}
		 brickcount1+=50;
	}
}
