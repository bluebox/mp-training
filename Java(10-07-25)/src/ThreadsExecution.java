
public class ThreadsExecution {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		Employee e1=new Employee();
//		e1.start();
		Manager m1=new Manager();
		Thread t2=new Thread(m1);
		t2.start();
		Employee e1=new Employee("t1");
		e1.start();
		System.out.println(e1.getName());
		Runnable r1=new Runnable()
				{
			public void run()
			{
				for(int i=0;i<3;i++)
				{
				System.out.println("using anonymous class");
				}
			}
				};
		Thread t3=new Thread(r1);
		Runnable r2=()->{for(int i=0;i<3;i++)
			{
			System.out.println("using lambda functions");
			}
		};
          Thread t4=new Thread(r2);
          t3.start();
          // t3.join();
          t3.sleep(10000);
           t4.start();
	}
}

class Employee extends Thread
{
	Employee(String name)
	{
		super(name);
	}
	public void run()
	{
		System.out.println("First way of multithreading");
	}
}
class Manager implements Runnable
{
	public void run()
	{
		System.out.println("Second way of multithreading");
	}
}
