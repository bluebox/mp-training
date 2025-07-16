package corejava.july10_creatingThread;


public class MyThread extends Thread {
	public void run() {
		System.out.println("In My Thread Class");
		DemoThread threadDemo=new DemoThread();
		Thread thread2=new Thread(threadDemo);
		thread2.start();
		try {
			System.out.println("My thread1 started sleeping");
			Thread.sleep(1);
			System.out.println("My thread1 is sleeping");
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		int count=0;
		for(int i=1;;i++) {
			if(i%2!=0) {
				System.out.println(i);
				count++;
			}
			if(count==5)
				break;
		}
	}
	
}
