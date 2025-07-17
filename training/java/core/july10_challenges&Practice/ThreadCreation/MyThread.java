package threadcreation;
import java.lang.*;
class MyThread extends Thread{
	public void run(){
		System.out.println("The running thread name:"+Thread.currentThread().getName());
		System.out.println("The status is "+this.getState());
		System.out.println("The first 5 even numbers:");
		for(int i=2;i<=10;i+=2) {
			System.out.println(i);
			try {
				Thread.sleep(250);
				
			}
			catch(InterruptedException e){
				e.printStackTrace();
				
			}
		}
	}
}
