package day_10_7_2025;

public class MultiThreadingexample extends Thread implements Runnable {
	
	@Override
	public void run() {
		System.out.println("theads created successfully"+Thread.MAX_PRIORITY+Thread.currentThread().getName());
		if(Thread.currentThread().getName().equals("Thread-0")) {
			for(int i=0;i<5;i++) {
				System.out.println(i*2+1);
			}
		}else {
			for(int i=0;i<5;i++) {
				System.out.println((i+1)*2);
			}
		}
		
		if(Thread.interrupted()) {
			System.out.println("Thread got interupted");
		}
	}
	
   public static void main(String [] args) {
	   
	   Thread thread1=new MultiThreadingexample();
	   thread1.start();
	   try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   thread1.interrupt();
	   
	   Thread thread2 = new Thread(() -> {
           System.out.println("Running the thread created using runnable Interface"+Thread.currentThread().getName());
       });
       thread2.start();
       thread2.interrupt();
	   
	   
	    Thread thread3=new Thread(new MultiThreadingexample());
	    thread3.start();
	    try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   
	   
   }
}
