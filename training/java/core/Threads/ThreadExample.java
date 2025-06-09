package Threads;

public class ThreadExample extends Thread {

	public static void main(String args[]) {
		ThreadExample newThread=new ThreadExample();
		newThread.start();
		System.out.println(newThread.getName());
		System.out.println(newThread.isAlive());
		ThreadExample newThread2=new ThreadExample();
		try {
			newThread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newThread2.start();
		System.out.println(newThread2.getName());
		System.out.println(newThread2.isAlive());
		
		System.out.println(newThread.getName());
		System.out.println(newThread.isAlive());
	}
	public void run() {
		System.out.println("thread started ");
	}
}
