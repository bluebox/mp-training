package Threads;

class Name {
    private int cnt = 0;

    public synchronized int addCnt() {
        this.cnt = this.cnt + 1;
        return this.cnt;
    }
}

public class SynchronizedThread {

    public static void main(String[] args) {
        Name val = new Name();

        Thread a = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                System.out.println(val.addCnt());
            }
        });

        Thread b = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                System.out.println( val.addCnt());
            }
        });

        a.start();
        b.start();
    }
}
