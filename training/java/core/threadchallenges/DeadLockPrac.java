package threadchallenges;



public class DeadLockPrac {
    private final Object pen = new Object();
    private final Object notes = new Object();

    public void methodA(){
        synchronized(notes){
            System.out.println(Thread.currentThread().getName() +"holding notes");
            try {
                wait(100);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            synchronized(pen){
                System.out.println(Thread.currentThread().getName() +"holding pen");
            }
        }
    }

    public void methodB(){
        synchronized(notes){
            System.out.println(Thread.currentThread().getName() +"holding notes");
            try {
                wait(100);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            synchronized(pen){
                System.out.println(Thread.currentThread().getName() +"holding pen");
            }
        }
    }

    public static void main(String[] args) {
        DeadLockPrac obj = new DeadLockPrac();

        Thread t1 = new Thread(()-> obj.methodA() , "Thread-1");
        Thread t2 = new Thread(()-> obj.methodB() , "Thread-2");

        t1.start();
        t2.start();

    }
}
