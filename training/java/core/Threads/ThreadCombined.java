package Threads;

public class ThreadCombined {
	public static class EvenThread extends Thread{
		public void run() {
			System.out.println("even thread ");

			try {
				for(int i=0;i<10;i+=2) {
					System.out.println(i);
				}
			}catch(Exception e) {
				System.out.println("even thread interupted");
			}
		}
	}
	public static void main(String[] args) {
		
		Runnable oddThread=()->{
			System.out.println("oddd thread ");

			try {
				for(int i=1;i<10;i+=2) {
					System.out.println(i);
				}
			}catch(Exception e) {
				System.out.println("odd thread interupted");
			}
		};
		
		EvenThread even=new EvenThread();
		
		Thread odd=new Thread(oddThread);
		even.start();
		odd.start();
	}

}
