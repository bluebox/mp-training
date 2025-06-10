package threads;
class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class ThreadDemo extends Thread {
    Counter counter;

    ThreadDemo(Counter counter) {
        this.counter = counter;
    }

    public synchronized void run() {
    	Thread current=Thread.currentThread();
        for (int i = 0; i < 100; i++) {
            counter.increment();
            System.out.println(i+" "+current.getName());
        }
    }
}

public class Synchonization {
    public static void main(String[] args) {
        Counter counter = new Counter();
        ThreadDemo t1 = new ThreadDemo(counter);
        ThreadDemo t2 = new ThreadDemo(counter);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + counter.getCount());
    }
}