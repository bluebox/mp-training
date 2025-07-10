package DeadLock;

import java.util.ArrayList;
import java.util.List;

public class Remove extends Thread {
	
	public int remove() throws InterruptedException {
		Numbers num= new Numbers();
		
		List<Integer> buffer = new ArrayList<>(num.getBuffer());
		
        synchronized (buffer) {
            while (buffer.isEmpty()) {
                System.out.println("Producer waiting: Buffer is Empty.");
                buffer.notify();
                buffer.wait(5000);
            }

            int rnum=buffer.remove(0);
            System.out.println("Removed: " + rnum + ", Buffer size: " + buffer.size());
           
        
        return rnum;
        }
    }

}
