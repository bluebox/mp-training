package Threads;
public class Threads {

    static class EvenThread extends Thread {
        public void run() {
            try {
                for (int i = 2; i <= 10; i += 2) {
                    if (Thread.interrupted()) {
                        throw new InterruptedException("EvenThread was interrupted!");
                    }
                    System.out.println("Even" + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("EvenThread exception: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        Runnable oddRunnable = () -> {
            try {
                for (int i = 1; i <= 9; i += 2) {
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException("OddRunnable was interrupted!");
                    }
                    System.out.println("Odd " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("OddRunnable exception: " + e.getMessage());
            }
        };
        EvenThread evenThread = new EvenThread();
        Thread oddThread = new Thread(oddRunnable);
        evenThread.start();
        oddThread.start();
        try {
            Thread.sleep(1500);
            System.out.println("Interrupting both threads");
            evenThread.interrupt();
            oddThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread: All threads finished.");
    }
}