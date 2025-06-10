import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPrinter {
    private final ReentrantLock lock = new ReentrantLock();

    public void print(String document, int copies) {
        lock.lock(); // acquire lock manually
        try {
            for (int i = 1; i <= copies; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing page " + i + " of " + document);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } finally {
            lock.unlock(); // release lock manually
        }
    }

    public static void main(String[] args) {
        ReentrantLockPrinter printer = new ReentrantLockPrinter();

        Runnable task1 = () -> printer.print("Document-X", 3);
        Runnable task2 = () -> printer.print("Document-Y", 3);

        Thread t1 = new Thread(task1, "User-A");
        Thread t2 = new Thread(task2, "User-B");

        t1.start();
        t2.start();
    }
}
