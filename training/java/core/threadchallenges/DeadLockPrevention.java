package threadchallenges;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class DeadLockPrevention {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void safeMethod1() {
        try {
            if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " locked Lock 1");

                Thread.sleep(50);

                if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " locked Lock 2");
                    lock2.unlock();
                } else {
                    System.out.println(Thread.currentThread().getName() + " failed to lock Lock 2");
                }
                lock1.unlock();
            } else {
                System.out.println(Thread.currentThread().getName() + " failed to lock Lock 1");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void safeMethod2() {
        try {
            if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " locked Lock 2");

                Thread.sleep(50);

                if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " locked Lock 1");
                    lock1.unlock();
                } else {
                    System.out.println(Thread.currentThread().getName() + " failed to lock Lock 1");
                }
                lock2.unlock();
            } else {
                System.out.println(Thread.currentThread().getName() + " failed to lock Lock 2");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DeadLockPrevention obj = new DeadLockPrevention();

        Thread t1 = new Thread(() -> obj.safeMethod1(), "Thread-1");
        Thread t2 = new Thread(() -> obj.safeMethod2(), "Thread-2");

        t1.start();
        t2.start();
    }
}
