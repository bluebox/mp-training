
package myThreads;

public class ThreadsDemo {
	public static void main(String[] args) throws InterruptedException {
		ThreadUsingSubClass thread1 = new ThreadUsingSubClass("thread-1");
		ThreadUsingSubClass thread2 = new ThreadUsingSubClass("thread-2");
		System.out.println("is alive thread1:" + thread1.isAlive());
		thread1.start();
		thread2.start();
		Thread.sleep(1000);
		thread1.interrupt();
		thread1.setPriority(1);
		thread2.setPriority(10);
		System.out.println("is " + thread1.getName() + " live " + thread1.isAlive());
		System.out.println("name : " + thread2.getName());
		System.out.println("id : " + thread2.getId());
		System.out.println("priority : " + thread2.getPriority());
		System.out.println("group : " + thread2.getThreadGroup());
		System.out.println("state : " + thread2.getState());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Interrupted Exception");
			e.printStackTrace();
		}
		System.out.println("Main thread " + Thread.currentThread().getName() + " is done.");

	}

}
