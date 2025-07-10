package Day11_10_07_practice;


class EvenThread extends Thread{
	public void run() {
		for(int i=0;i<=10;i+=2) {
			System.out.println("Even:"+i);
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
public class ThreadChallenge {
	
	public static void main(String[] args) {
		Runnable OddThread=()->{
				for(int i=1;i<=9;i+=2) {
					System.out.println("Odd:"+i);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
		};
		Thread runnableThread=new Thread(OddThread);
		EvenThread eve=new EvenThread();
		eve.start();
		runnableThread.start();
		
		
	}
}
