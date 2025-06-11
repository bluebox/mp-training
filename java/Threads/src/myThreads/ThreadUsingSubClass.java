package myThreads;

public class ThreadUsingSubClass extends Thread {
//	Thread thread = new Thread();
	public ThreadUsingSubClass(String name) {
		super(name);
	}
	public void run() {
	try {
	for (int i =1; i<=10;i+=2) {
		System.out.println("The value of i :"+i+" "+Thread.currentThread().getName());
		Thread.sleep(1000);
	}
	}
	catch (Exception e) {
		System.out.println(Thread.currentThread().getName()+" was interrupted");
	}
	Thread.yield();
	}
}
