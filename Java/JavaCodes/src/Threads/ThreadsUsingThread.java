package Threads;
class MyThread extends Thread{
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
public class ThreadsUsingThread {

	public static void main(String[] args) throws InterruptedException {
		MyThread a=new MyThread();
		MyThread b=new MyThread();
		System.out.println(a.getId());
		System.out.println(a.getPriority());
		System.out.println(a.getName());
		a.setName("Anand");
		System.out.println(a.getName());
		a.start();
		b.start();
		a.join();
	}

}
