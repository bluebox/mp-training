package threadChallenge;

public class ThreadClass extends Thread{
	@Override
	public void run() {
		 int count =0;
		 int i=0;
		 while(count < 5) {
			 System.out.println(i  +"   "+ Thread.currentThread().getName());
			try {
				Thread.sleep(500);
				i=i+2;
				count++;
			} catch (InterruptedException e) {
				System.out.println("Interrupted "+e.getMessage());
				break;
			}
		 }
	}
}
