package Threads;

public class ThreadMethods extends Thread {

	@Override
	public void run() {
		System.out.println("Thread started running.");

		for (int i = 1; i <= 5; i++) {
			System.out.println("Working... step " + i);

			if (i == 3) {
				System.out.println("Thread yielding at step " + i);
				Thread.yield();
			}

			try {
				System.out.println("Thread sleeping for 1 second...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Thread was interrupted during sleep!");
				return;
			}

			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Thread detected interrupt signal, stopping...");
				return;
			}
		}

		System.out.println("Thread finished work.");
	}

	public static void main(String[] args) {
		ThreadMethods t = new ThreadMethods();

		System.out.println("Before start(),thread state: " + t.getState());
		t.start();
		System.out.println("After start(),thread state: " + t.getState());

		try {
			System.out.println("Main thread waiting for worker thread to finish (join)...");
			t.join();
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted while waiting.");
		}

		System.out.println("Worker thread has finished.");

		System.out.println("Attempting to interrupt the thread after completion.");
		t.interrupt();

		System.out.println("Main thread exiting.");
	}
}
