package day11;

public class EvenOddMain {
	public static void main(String[] args) {
		Thread evens = new EvenNumberThread();
		Thread odds = new Thread(new OddNumberRunnable());
		evens.start();
		odds.start();
	}
}
