package July10.ThreadChallenge;

public class Even extends Thread {

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			try {
				if (i % 2 == 0)
					System.out.println("Even Number " + i);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Exception occured");
			}
		}
	}

}
