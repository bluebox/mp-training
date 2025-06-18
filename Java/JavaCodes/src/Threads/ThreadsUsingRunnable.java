package Threads;
class mythread implements Runnable{

	@Override
	 public void run() {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
	
}
public class ThreadsUsingRunnable {

	public static void main(String[] args) {
		Thread a=new Thread(new mythread());
		a.start();
		Thread b=new Thread(()->{
			for(int i=0;i<4;i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(i);
			}
		});
		b.start();
	}

}
