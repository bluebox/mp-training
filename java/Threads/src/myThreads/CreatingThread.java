package myThreads;


public class CreatingThread{
	public static void main(String[] args) {
		ThreadUsingSubClass  thread1 = new ThreadUsingSubClass("thread");
		Thread thread2 = new Thread(new ThreadUsingRunnable());
		System.out.println("----------Using run() method------------");
		thread1.run();
		thread2.run();
		System.out.println("----------Using start() method------------");
		thread1.start();
		thread2.start();
	}
	

}
