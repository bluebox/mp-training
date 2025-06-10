package learn;


class Thread1 extends Thread{

	@Override
	public void run() {
		System.out.println("Thread1 extended from thread class running successfully ");
	}
		
}


class ThreadRunnable implements Runnable{

	@Override
	public void run() {
		
		System.out.println("Thread2 implemented from runnable interface running successfully ");
	}
	
	
}

public class MainThread {
	
	public static void main(String[] args) {
		
		Thread1 thread1 = new Thread1();
		ThreadRunnable t= new ThreadRunnable();
		Thread thread2 = new Thread(t);
		
		thread1.start();
		
		thread2.start();
		
		
	}
}
