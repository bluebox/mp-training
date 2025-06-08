public class Practice_1 {

    
    Thread t1 = new Thread(new Task());

    // OR without creating a new class Task which implements Runnable interface, we can directly create a task object using lambda expression.

    Thread t2 = new Thread(() -> {
        System.out.println("Hello there");
    });

    public static void main(String[] args) {
        Practice_1 p1 = new Practice_1();
        p1.t2.start();
    }
}

class Task implements Runnable {
    @Override
    public void run()
    {
        System.out.println("Hello there");
    }
}