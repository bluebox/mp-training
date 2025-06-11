package java;

public class ThreadsChallenge1{
    public static void main(String[] args){

     EvenThread a=new EvenThread();
     OddThread b=new OddThread();
     Thread thread=new Thread(b);
     //Thread b=new Thread(new OddThread());
     a.start();
     thread.start();

    }
}


