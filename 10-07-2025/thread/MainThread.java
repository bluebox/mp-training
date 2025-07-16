package ThreadBasic;

import java.lang.Thread;

public class MainThread {
	
	public static void main(String[] args) throws InterruptedException{
		
		RunnableClass rc=new RunnableClass();
		
		Thread t1=new Thread(rc);
		ClassThread t2=new ClassThread() ;
		t1.start();
		t2.start();
		t1.interrupt();
		
	}

}
