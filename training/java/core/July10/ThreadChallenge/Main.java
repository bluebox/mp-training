package July10.ThreadChallenge;

public class Main {
	
	public static void main(String[] args) {

		Thread odd = new Thread(new Odd());
		Thread even = new Even();
		
		odd.start();
		odd.interrupt();
		even.start();
		
	}
}