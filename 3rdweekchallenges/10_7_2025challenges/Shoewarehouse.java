import java.util.LinkedList;
import java.util.Queue;

public class Shoewarehouse extends Thread{
    public  int size;
    public static Queue<Integer> q=new LinkedList<>();
    public Shoewarehouse(int size){
        this.size=size;

    }
    public void run(){
        System.out.println("shoewarehouse is created");
    }
    public synchronized void receiveorder(int num){
        if(q.size()==this.size){
            try {
                System.out.println("queue is full please wait");
                wait();
            } catch (Exception e) {
                System.err.println("exception");
            }
        }
        else if(q.size()<this.size){
            notifyAll();
        }
        q.add(num);

    }
    public synchronized void produceorder(){
        if(q.isEmpty()){
            try {
                System.out.println("queue is empty");
                wait();
            } catch (Exception e) {
                System.out.println("exception"+e);
            }
        }
        else if(q.size()<this.size){
            notifyAll();
        }
        q.remove();
    }
    
}
