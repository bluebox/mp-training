package July10.Practice;

public class main2 extends Thread {

	@Override
	public void run() {
		
		for (int i = 0; i < 5; i++) {
			try {
				System.out.print("1 ");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
