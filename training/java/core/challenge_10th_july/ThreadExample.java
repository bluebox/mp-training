package challenge_10th_july;

public class ThreadExample {
    public static void main(String[] args) {
    	
        MyRunnable myRunnable = new MyRunnable();
        MyThread threadObject = new MyThread();
        Thread thread1 = new Thread(myRunnable);
        thread1.start();
        threadObject.start();
        System.out.println("Main thread finished.");
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
    	System.out.println("Runnable Thread started");
        for (int i = 0; i < 10; i+=2) {
            System.out.println("even numbers : "+i);
        }
    }
}
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread class started");
       
        for (int i = 1; i < 10; i+=2) {
            System.out.println("odd numbers : "+i);
        }
    }
}
