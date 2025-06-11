package java;

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
