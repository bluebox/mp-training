package corejava.july10_creatingThread;

public class DemoThread implements Runnable {

	@Override
	public void run() {
		System.out.println("In Demo thread");
		int count=0;
		try {
			System.out.println("My thread2 started sleeping");
			Thread.sleep(2);
			System.out.println("My thread2 is sleeping");
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		for(int i=1;;i++) {
			if(i%2==0) {
				System.out.println(i);
				count++;
			}
			if(count==5)
				break;
		}
	}
}
