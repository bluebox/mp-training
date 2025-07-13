package threadChallenge;

public class ThreadChallenge {
	public static void main(String[] args) {
		
		 Runnable runnable = ()->{
			int count =0;
			 int i=1;
			 while(count < 5) {
				 System.out.println(i +"   "+ Thread.currentThread().getName());
				try {
					Thread.sleep(500);
					i=i+2;
					count++;
				} catch (InterruptedException e) {
					System.out.println("Thread Interrupted ");
					break;
				
				}
			 }
		 };
		 
		 ThreadClass threadClass = new ThreadClass();
		 
		 Thread thread2 = new Thread(runnable);
		 
		 threadClass.start();
		 thread2.start();
		 
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 thread2.interrupt();
 
	}
}