package threadchallenges;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DeadLockUsingLock {

    private final Lock pen = new ReentrantLock();
    private final Lock notes = new ReentrantLock();

    public void methodA(){
        pen.lock();
        System.out.println(Thread.currentThread().getName() +"holding pen");
        try {
            Thread.sleep(100);
            notes.lock();
            System.out.println(Thread.currentThread().getName() +"holding notes");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally{
            notes.unlock();
            pen.unlock();
        }

    }
    

    public void methodB(){
        notes.lock();

        System.out.println(Thread.currentThread().getName() +"holding notes");
        try {
            Thread.sleep(100);
            pen.lock();
            System.out.println(Thread.currentThread().getName() +"holding pen");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

       finally{
        pen.unlock();
        notes.unlock();
       }
    }
    

    public static void main(String[] args) {
        DeadLockUsingLock obj = new DeadLockUsingLock();

        Thread t1 = new Thread(()-> obj.methodA() , "Thread-1");
        Thread t2 = new Thread(()-> obj.methodB() , "Thread-2");

        t1.start();
        t2.start();

    }
}
