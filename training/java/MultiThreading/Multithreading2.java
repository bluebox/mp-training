package MultiThreading;

public class Multithreading2 {
    public static void main(String[] args) throws InterruptedException {
        Runnable r1= new Runnable(){
            public void run() {
                    for(int i=0; i<5; i++) {
                        System.out.println(Thread.currentThread().getName()+" is running");
                    }
                }
        };
        Runnable r2= new Runnable(){
            public void run() {
                for(int i=0; i<8; i++) {
                  //  System.out.println(Thread.currentThread().getName()+" is running");
                }
            }
        };
        Thread t1=new Thread(r1, "Thread1");
        Thread t2=new Thread(r2, "Thread2");
        System.out.println(t1.getState());
        t1.start();
         System.out.println(t1.getState());
        //t1.join();
        t1.sleep(1000); // Sleep for 1 second
        System.out.println(t1.getState());
        t2.start();
    }
}
