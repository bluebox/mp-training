public class Bharat  extends Thread{
    public void run(){
        System.out.println("this is main  thread");
        
    }
    public static void main(String[] args) {
        
       Bharat br = new Bharat();

        Rohan rr= new Rohan();

        Thread tr= new Thread(rr);
        br.start();
        tr.start();
        for(int j=0;j<5;j++){
            System.out.println("odd numbers are " + ((j*2)+1));
        }
    }
    
}

class Rohan implements Runnable{
    public void run(){
           System.out.println("this is thread from runnable ");
        for(int i=0;i<5;i++){
            System.out.println("even numbers are " + i*2);
        }
     

    }
}