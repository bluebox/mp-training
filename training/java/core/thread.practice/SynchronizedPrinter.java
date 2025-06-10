public class SynchronizedPrinter {
    public synchronized void print(String document, int copies) {
        for (int i = 1; i <= copies; i++) {
            System.out.println(Thread.currentThread().getName() + " is printing page " + i + " of " + document);
            try {
                Thread.sleep(500); // simulate print time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedPrinter printer = new SynchronizedPrinter();

        Runnable task1 = () -> printer.print("Document-A", 3);
        Runnable task2 = () -> printer.print("Document-B", 3);

        Thread t1 = new Thread(task1, "User-1");
        Thread t2 = new Thread(task2, "User-2");

        t1.start();
        t2.start();
    }
}
