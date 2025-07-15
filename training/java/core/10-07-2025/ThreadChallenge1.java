package Threads;

public class ThreadChallenge1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		C c=new C();
		c.start();
	
		try {
			c.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Runnable r=()->{
			for(int i=1;i<10;i+=2)
			{
				System.out.println(i);
			}
		};
		
		Thread t1=new Thread(r);
		t1.start();
		
		

	}

}

class C extends Thread
{
	public void run()
	{
		for(int i=0;i<10;i+=2)
		{
			System.out.println(i);
		}
	}
}