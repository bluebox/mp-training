//Sample Thread Example using Runnable Interface along with Anonymous classes

package Threads;

public class Thread3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable r1=new Runnable()
				{
			        public void run()
			        {
			        	System.out.println("hello");
			        }
			
				};
				
		Runnable r2=new Runnable()
		{
			
			public void run()
			{
				System.out.println("Welocme");
			}
	    };
	    
	    Thread t1=new Thread(r1);
	    t1.start();
	    Thread t2=new Thread(r2);
	    t2.start();
		

	}

}

class A implements Runnable
{
	public void run()
	{
		System.out.println("This is class A");
	}
}

class B implements Runnable
{
	public void run()
	{
		System.out.println("This is class B");
	}
}
