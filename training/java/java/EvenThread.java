package java;

public class EvenThread extends Thread {

    public void run() {
        for (int i = 0; i < 11; i = i + 2) {
            System.out.println("Even:" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("innterapted");
                break;
            }

        }
    }
}
