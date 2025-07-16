package day11;


public class ThreadChallenge {
	static class evenThread extends Thread{
		public void run() {
			try {
				for(int i=2;i<=10;i+=2) {
					if(isInterrupted()) {
						System.out.println(" thread is interrupted ");
						return;
					}
					System.out.println("even thread : "+i);
					Thread.sleep(500);
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		evenThread even=new evenThread();
		Thread oddThread=new Thread(()->{
			try {
				for(int i=1;i<=9;i+=2) {
					if(Thread.currentThread().isInterrupted()) {
						System.out.println(" thread is interrupted");
						return;
					}
					System.out.println("odd thread : "+i);
					Thread.sleep(500);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		});
		
		even.start();
		oddThread.start();
		
		try {
			Thread.sleep(2000);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		even.interrupt();
		oddThread.interrupt();
		
		try {
			even.join();
			oddThread.join();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}


