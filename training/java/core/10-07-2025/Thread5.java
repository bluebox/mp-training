//Sample Example for Thread Lifecycle Methods

package Threads;

public class Thread5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable r1=()->{
			System.out.println("hii");
		};
		
		Runnable r2=()->{
			System.out.println("hello");
		};
		
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		
		System.out.println(t1.getState());
		t1.start();
		System.out.println(t1.getState());
		try {
			t1.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(t1.getState());
		t2.start();
	}

}
