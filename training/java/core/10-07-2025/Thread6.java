package Threads;

public class Thread6 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		Bricks b1=new Bricks();
		Runnable r1=()->{
			for(int i=0;i<10000;i+=50)
			{
				b1.incbrickCount();
			}
		};
		
		Runnable r2=()->{
			for(int i=0;i<15000;i+=50)
			{
				b1.incbrickCount();
			}
		};

		Runnable r3=()->{
			for(int i=0;i<5000;i+=50)
			{
				b1.incbrickCount();
			}
		};
		
		
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		Thread t3=new Thread(r3);
		
		t1.start();
		
		t2.start();
		
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		
		System.out.println(b1.brickcount);
		System.out.println(b1.brickcount2);


	}

}

class Bricks
{
//	int brickCount=0;
//	public synchronized void incbrickCount()
//	{
//		brickCount+=50;
//	}
	
	int brickcount=0;
	int brickcount2=0;
	
	public void incbrickCount()
	{
		synchronized(this) {
			brickcount+=50;
		}
		
		brickcount2+=50;
		
	}
}
