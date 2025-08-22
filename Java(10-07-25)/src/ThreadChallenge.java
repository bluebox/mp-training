
public class ThreadChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class1 c1=new Class1();
		c1.start();
		Class2 c2=new Class2();
		Thread t1=new Thread(c2);
		t1.start();
		
	}

}
class Class1 extends   Thread
{
	public void run()
	{
		for(int i=2;i<=10;i+=2)
		{
			System.out.println(i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Class2 implements Runnable
{
	@Override
	public void run() {
		for(int i=1;i<=9;i+=2)
		{
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}	
}
