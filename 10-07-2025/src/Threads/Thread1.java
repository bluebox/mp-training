//Sample Thread Example using thread class

package Threads;

public class Thread1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(Thread.currentThread().getName());
//		System.out.println(Thread.activeCount());
//		System.out.println(Thread.currentThread().getPriority());
          Employee e=new Employee();
          Manager m=new Manager();
          Manager1 m1=new Manager1();
          Manager2 m2=new Manager2();
          m.start();
          m1.start();
          m2.start();
          e.start();
          System.out.println(e.activeCount());	
		
	}

}

class Employee extends Thread
{
	public void run()
	{
		System.out.println("Hello");
	}
}

class Manager extends Thread
{
    public void run()
	{
		System.out.println("This is manager");
	}
}


class Manager1 extends Thread
{
    public void run()
	{
		System.out.println("This is manager1");
	}
}

class Manager2 extends Thread
{
    public void run()
	{
		System.out.println("This is manager2");
	}
}