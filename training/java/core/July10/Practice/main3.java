package July10.Practice;

public class main3 implements Runnable {

	@Override
	public void run() {

		for (int i = 0; i < 8; i++) {
			try {
				System.out.print("2 ");
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}