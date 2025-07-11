package day11;

public class EvenNumberThread extends Thread {

	@Override
	public void run() {
		int counter = 1;
		int number = 0;
		while (counter <= 5) {
			System.out.print(number + " ");
			number += 2;
			counter++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
