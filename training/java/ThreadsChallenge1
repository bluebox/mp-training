
public class ThreadChallenge{
    public static void main(String[] args){

     EvenThread a=new EvenThread();
     OddThread b=new OddThread();
     Thread thread=new Thread(b);
     //Thread b=new Thread(new OddThread());
     a.start();
     thread.start();

    }
}




public class EvenThread extends Thread {
    
    public void run(){
        for(int i=0;i<11;i=i+2){
            System.out.println("Even:"+i);
            try{Thread.sleep(500);
        }catch(InterruptedException e){
            System.out.println("innterapted");
            break;
        }

        }
    }
}

public class OddThread implements Runnable {
    
    public void run(){
        for(int i=1;i<11;i=i+2){
            System.out.println("odd:"+i);
            try{Thread.sleep(500);
        }catch(InterruptedException e){
            System.out.println("innterapted");
            break;
        }

        }
    }
}

