

class TempThread extends Thread{

        public void run(){
                if(Thread.interrupted()){
                System.out.println("this thread1 is interrupted");
                return;
            }
            System.out.println("5 even numbers");
            for(int i=0;i<5;i++){
                System.out.println(i*2);
            }
            System.out.println("this is thread1");

        }
    
}
class Runnablethred implements Runnable {
    public void run(){
         if(Thread.interrupted()){
                System.out.println("this thread2 is interrupted");
                return;
            }
        System.out.println("this is thread2");
        System.out.println("5 odd numbers");
            for(int i=0;i<5;i++){
                System.out.println(i*2+1);
            }
    }

    
}

public class Threadpractise{
    public static void main(String[] args) {
        TempThread t1=new TempThread();
       TempThread t3=new TempThread();
       TempThread t4=new TempThread();
        Runnablethred r1=new Runnablethred();
        Thread t2=new Thread(r1);
         t1.start();
         t3.start();
         t1.interrupt();
         
        t2.start();
        try {
            Thread.sleep(1000);
            
        } catch (Exception e) {
            
        }
        
        t4.start();
        t2.interrupt();

        


    }
}
