package ThreadBasic;

import java.lang.Runnable;
public class RunnableClass implements Runnable{
	
	public void run() {
		
		try {
			for(int i=1;i<=9;i+=2) {
				System.out.println(i+" ");
				Thread.sleep(100);
			}
		}
		catch(InterruptedException e) {
			System.out.println("Thread Interrupted");
		}
	}

}
