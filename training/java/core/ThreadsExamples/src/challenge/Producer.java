package challenge;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<String> buffer;
    private static int empId = 1;

    public Producer(BlockingQueue<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String emp = "Emp" + getNextId();
                buffer.put(emp); // waits if full
                System.out.println(Thread.currentThread().getName() + " Produced: " + emp);
                Thread.sleep(1000); // simulate processing time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private synchronized static int getNextId() {
        return empId++;
    }
}
