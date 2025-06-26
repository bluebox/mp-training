package Threads;

class MyRunnable implements Runnable{
	@Override
	public void run() {
		for(int i=2; i<=10; i+=2) {
			try {
				System.out.println("even number : "+i);
		        Thread.sleep(500);
		    } catch (InterruptedException e) {
		        // We've been interrupted: no more messages.
		        return;
		    }

			
			
		}
	}
}

class MyThread extends Thread{
	@Override
	public void run() {
		for(int i=1; i<10; i+=2) {
			try {
				System.out.println("odd number : "+i);
		        Thread.sleep(500);
		    } catch (InterruptedException e) {
		        // We've been interrupted: no more messages.
		        return;
		    }
			
		}
	}
}

public class ThreadChallenge {
	
	public static void main(String args[]) throws InterruptedException {
		MyThread threadA = new MyThread();
		MyRunnable runnable = new MyRunnable();
		Thread threadB = new Thread(runnable);
		threadB.join();
		threadA.start();
		threadB.start();
		
	}

}
