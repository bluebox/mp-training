package Threads;

public class Odd extends Thread {
	public void run() {
		for (int i = 1; i <= 100; i++) {
			try {
				if (i % 2 == 1) {
					System.out.println("Odd Thread : " + i);
				}
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				System.out.println("Odd Thread is interrupted");
				Thread.currentThread().interrupt();
			}
			
		}
		
	}
}
