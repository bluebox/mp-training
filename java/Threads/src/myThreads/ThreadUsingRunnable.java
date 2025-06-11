package myThreads;

public class ThreadUsingRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=2;i<=10;i+=2) {
			System.out.println("the value of i :"+ i+  " "+Thread.currentThread().getName());
		}
		
	}

}
