package Threads;

public class Even implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			if (i % 2 == 0) {
				System.out.println("Even Thread : " + i);
			}
		}
		try {
			Thread.sleep(1000);
		}
		catch(InterruptedException e) {
			System.out.println("Even Thread is interrupted");
			Thread.currentThread().interrupt();
		}
		
	}

}
