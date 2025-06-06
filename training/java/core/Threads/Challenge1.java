package training.java.core.Threads;

public class Challenge1 {
    public static void main(String[] args) {
        PrintsEven p1= new PrintsEven();
        PrintsOdd p2= new PrintsOdd();
        p1.start();
        p2.start();
        // System.out.println("in main");
        // System.out.println("in main");
        // System.out.println("in main");
        // System.out.println("in main");
        
    }
}
class PrintsOdd extends Thread{
    public void run(){
        int n=5;
        int c=1;
        for(int i=1;i<=n;i++){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("odd");
            c+=2;
        }
    }
}

class PrintsEven implements Runnable{
    @Override
    public void run(){
        int n=5;
        int c=2;
        for(int i=1;i<=n;i++){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("even");
            c+=2;
        }
    }

    private void sleep(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sleep'");
    }
}