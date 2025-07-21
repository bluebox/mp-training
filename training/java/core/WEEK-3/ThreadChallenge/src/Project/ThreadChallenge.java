package Project;

public class ThreadChallenge {
    public static void main(String[] args) {
        EvenNumberThread evenThread = new EvenNumberThread();
        Runnable oddRunnable = () -> {
            for (int i = 1; i <= 10; i += 2) {
                System.out.println("Odd: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Odd thread interrupted!");
                    break;
                }
            }
        };
        Thread oddThread = new Thread(oddRunnable);

        evenThread.start();
        oddThread.start();
        try {
            Thread.sleep(3000);
            System.out.println("Interrupting even thread...");
            evenThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class EvenNumberThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 8; i += 2) {
            System.out.println("Even: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Even thread interrupted!");
                break;
            }
        }
    }
}

