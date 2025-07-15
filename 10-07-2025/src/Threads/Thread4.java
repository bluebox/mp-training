//Sample Thread Example using Runnable Interface along with lambda Expressions

package Threads;

public class Thread4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Runnable r1=()->{
			
//			System.out.println("hello");
			
			for(int i=1;i<=20;i++)
			{
				System.out.println(i);
			}
			
		};
		Runnable r2=()->
		{
//			System.out.println("Welcome");
			for(int i=20;i<=30;i++)
			{
				System.out.println(i);
			}
		};
		
		
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		t1.start();
		
//		try {
//			t1.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			t1.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t2.start();
				

	}

}
