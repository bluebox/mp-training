package threadcreation;
import java.lang.Thread;
import java.lang.Runnable;




public class Main {
	public static void main(String[] args) {
		MyThread t1=new MyThread();
		t1.start();
		//t1.run();
		Thread t2=new Thread(new ThreadInterface());
		t2.start();
		//t2.run();
		
	}
	
}
