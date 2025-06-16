package Threads;

class Multiple extends Thread
{
	public synchronized void  run() {
		int n=5;
		for(int i=1;i<11;i++)
		{
			System.out.println(i+" * "+n+" ="+i*n);			
		}
	}
}
public class ThreadExample {

	public static void main(String args[])
	{
		Multiple th1=new Multiple();
		Multiple th2=new Multiple();
		Multiple th3=new Multiple();
		
		th1.start();
		th2.start();
		th3.start();
	}
}
