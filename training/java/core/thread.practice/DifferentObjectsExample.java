class Printer {
    public synchronized void printDoc(String name) {
        System.out.println(Thread.currentThread().getName() + " entered " + name);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println(Thread.currentThread().getName() + " exiting " + name);
    }
}

public class DifferentObjectsExample {
    public static void main(String[] args) {
        Printer printer1 = new Printer();
        Printer printer2 = new Printer(); // different object

        Thread t1 = new Thread(() -> printer1.printDoc("Printer1"));
        Thread t2 = new Thread(() -> printer2.printDoc("Printer2"));

        t1.start();
        t2.start();
    }
}
