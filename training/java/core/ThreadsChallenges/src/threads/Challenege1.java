package threads;

public class Challenege1 {

	public static void main(String[] args) throws InterruptedException {
		ThreadA threadA=new ThreadA(); 
		
		ThreadB threadB=new ThreadB();
		Thread tb=new Thread(threadB);
		threadA.start();				
		tb.start();
		
	}

}
class ThreadA extends Thread {
	
	public void run()
	{
		printOdd();
	}
	private void printOdd() 
	{
		Thread current=Thread.currentThread();
		for(int i=0;i<10;i+=2)
		{
			try {
				current.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i+" "+current.getName());
			
		}
	}
	
	
}
class ThreadB implements Runnable{

	@Override
	public void run() {
		printOdd();	
	}
	private void printOdd()
	{
		
		Thread current=Thread.currentThread();
		for(int i=1;i<10;i+=2)
		{
			try {
				current.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i+" "+current.getName());
			
		}
	}
	
}
