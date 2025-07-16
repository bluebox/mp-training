package dev.tulasidhar.personalpractice;

public class SayHiThread extends Thread{
	@Override
	public void run() {
		for(int i=0 ; i<5 ; i++) {
			System.out.println("hello from extended thread " + Thread.currentThread());;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
