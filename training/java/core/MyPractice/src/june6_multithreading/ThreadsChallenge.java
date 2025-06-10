package june6_multithreading;

public class ThreadsChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EvenNumbers evenNumbers=new EvenNumbers();
		OddNumbers on=new OddNumbers();
		Thread oddNumbers=new Thread(on);
		oddNumbers.start();
		evenNumbers.start();
	}

}
class EvenNumbers extends Thread{
	@Override
	public void run() {
		for(int i=2;i<=10;i+=2) {
			System.out.print(i+" ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
}
class OddNumbers implements Runnable{

	@Override
	public void run() {
		for(int i=1;i<=10;i+=2) {
			System.out.print(i+" ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	
}