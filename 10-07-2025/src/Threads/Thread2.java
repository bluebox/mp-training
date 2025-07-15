//Sample Thread example using Runnable Interface
package Threads;

public class Thread2 {

	public static void main(String[] args) {
		Name1 n1=new Name1();
		Name2 n2=new Name2();
		Thread t1=new Thread(n1);
		Thread t2=new Thread(n2);
		t1.start();
		t2.start();
		
	}

}
class Name1 implements Runnable
{
	public void run()
	{
		System.out.println("This is name1 class");
	}
}

class Name2 implements Runnable
{
	public void run()
	{
		System.out.println("this is name2 class");
	}
}
