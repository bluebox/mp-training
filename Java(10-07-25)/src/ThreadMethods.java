
public class ThreadMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		System.out.println("name of the thread "+Thread.currentThread().getName());
		System.out.println("id of the thread "+Thread.currentThread().getId());
		System.out.println("priority of main thread "+Thread.currentThread().getPriority());
		Thread t1=new Thread();
		System.out.println(Thread.activeCount());
		t1.start();
		System.out.println("Count of the threads after start method "+Thread.activeCount());
		System.out.println("priority of created  thread before setting priority "+t1.getPriority());
		t1.setPriority(7);
		System.out.println("priority of created  thread "+t1.getPriority());
	}

}
