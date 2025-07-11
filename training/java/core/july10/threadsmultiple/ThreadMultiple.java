package dev.tulasidhar.july10.threadsmultiple;

public class ThreadMultiple {
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				
				for(int i=0 ; i<10 ; i++) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("this is runnable-thread");
				}
			}
		};
		
		Thread thread1 = new Thread(runnable);
		
		Thread thread2 = new Thread(){
			public void run() {
				for(int i=0 ; i<10 ; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("this is thread from thread class");
				}
			}
		};
		
		thread1.start();

		thread2.start();
	}
}
