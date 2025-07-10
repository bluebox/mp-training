class MyThread extends Thread {
	public void run() {
		int count=0;
		for(int i=0;i<=10;i++) {
			if(i%2==0) {
				System.out.println(i);
				count++;
				if(count==5) break;
				try {
					Thread.sleep(500);
				} 
				catch (InterruptedException e)  {
					e.printStackTrace();
				}
			}
		}
	}
}
class MyRunnable implements Runnable{
	public void run() {
		int count=0;
		for(int i=0;i<=10;i++) {
			if(i%2!=0) {
				System.out.println(i);
				count++;
				if(count==5) break;
				try {
					Thread.sleep(500);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
public class Main {

	public static void main(String[] args) throws InterruptedException{
		MyThread t1=new MyThread();
		MyRunnable r=new MyRunnable();
		Thread t2=new Thread(r);
		t1.start();
		t2.start();
		//t1.interrupt();
	}

}
