package myThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i = 1; i <= 8; i++) {
			final int taskId=i;
			executor.submit(()->{
				System.out.println("Task "+ taskId + " running on "+Thread.currentThread().getName());
				try {
					Thread.sleep(3000);
				}
				catch(InterruptedException e) {
					System.out.println("Task "+taskId+" Interrupted");
				}
			});
		}
	}
}
