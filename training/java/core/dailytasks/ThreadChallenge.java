public class ThreadChallenge {

    static class EvenNumbersThread extends Thread {
        @Override
        public void run() {
            System.out.println("EvenNumbersThread started.");
            try {
                for (int i = 0; i < 5; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("EvenNumbersThread interrupted!");
                        break;
                    }
                    System.out.println("Even: " + (2 * (i + 1)));
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("EvenNumbersThread was interrupted while sleeping.");
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("EvenNumbersThread finished.");
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Main Thread started.");

        EvenNumbersThread evenThread = new EvenNumbersThread();

        Runnable oddNumbersRunnable = () -> {
            System.out.println("OddNumbersRunnable started.");
            try {
                for (int i = 0; i < 5; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("OddNumbersRunnable interrupted!");
                        break;
                    }
                    System.out.println("Odd: " + (2 * i + 1));
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("OddNumbersRunnable was interrupted while sleeping.");
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("OddNumbersRunnable finished.");
            }
        };

        Thread oddThread = new Thread(oddNumbersRunnable);

        evenThread.start();
        oddThread.start();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while waiting.");
        }

        System.out.println("Main Thread: Attempting to interrupt EvenNumbersThread.");
        evenThread.interrupt();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while joining threads.");
        }

        System.out.println("Main Thread finished.");
    }
}