package threads12;

class MyThread implements Runnable 
{    
  	// Method to start Thread
    public void run()
    {
      	String str = "Thread is Running Successfully";
        System.out.println(str);
        System.out.println("Current Thread running " + Thread.currentThread().getName());
        System.out.println("Current Thread Group " + Thread.currentThread().getThreadGroup());
    }

}

class ThreadMethods extends Thread{
    @Override
    public void run() {
        System.out.println("run method called ");
        System.out.println("Current Thread running " + currentThread().getName());
        System.out.println("Current Thread Group " +currentThread().getThreadGroup());
    }

}

public class Threads123
{
    public static void main(String[] args)
    {
        MyThread g1 = new MyThread();
      
        // initializing Thread Object
        Thread t1 = new Thread(g1);
        
      	// Running Thread
      	t1.start();
      	
      	
      	ThreadMethods threadMethods = new ThreadMethods();
        threadMethods.start();
    }
}
