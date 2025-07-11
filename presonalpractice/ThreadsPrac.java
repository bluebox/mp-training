package dev.tulasidhar.personalpractice;

public class ThreadsPrac {
	public static void main(String[] args) {
		PrintHi hi = new PrintHi();
		Thread sayHi = new Thread(hi);
		
		Thread sayBye = new Thread(()->{
			try {
				for(int i=0 ; i<5 ; i++) {
					System.out.println("Bye! in " + Thread.currentThread().getName());
					Thread.sleep(500);
				}
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		
		hi.run();
		sayBye.start();
		
		Thread hiThread = new SayHiThread();
		hiThread.start();
	}
	
	
}

class PrintHi implements Runnable{
		public void run() {
			for(int i=0 ; i<5 ; i++) {
				try {
					Thread.sleep(1000);
					System.out.println("Hi in " + Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

}

