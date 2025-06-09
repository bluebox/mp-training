package Threads;
//using runnable
public class ThreadExample2 implements Runnable {

	@Override
	public void run() {
		System.out.println("Thread started " + Thread.currentThread().getName());
	}
	
	
	public static void main(String[] args) {
		ThreadExample2 thread1=new ThreadExample2();
		Thread newThread=new Thread(thread1);
		newThread.start();
	}
}
