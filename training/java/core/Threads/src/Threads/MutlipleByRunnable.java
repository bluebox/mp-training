package Threads;

class Multiple implements Runnable
{
	public synchronized void  run() {
		int n=5;
		for(int i=1;i<11;i++)
		{
			System.out.println(i+" * "+n+" ="+i*n);			
		}
	}
}
public class MutlipleByRunnable {

	public static void main(String args[])
	{
		Thread th1=new Thread(new Multiple());
		Thread th2=new Thread(new Multiple());
		Thread th3=new Thread(new Multiple());
		
		th1.start();
		th2.start();
		th3.start();
	}
}
