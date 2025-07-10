package DeadLock;

import java.util.ArrayList;
import java.util.List;

public class Numbers extends Thread {
	
	private List<Integer> buffer = new ArrayList<>();
    private  int capacity = 5;
    
    

    public List<Integer> getBuffer() {
		return buffer;
	}



	public int getCapacity() {
		return capacity;
	}


	public void add(int number) throws InterruptedException {
        synchronized (buffer) {
            while (buffer.size() == capacity) {
                System.out.println("Adder Waiting: Buffer is full.");
                buffer.notify();
                buffer.wait(5000);
            }

            buffer.add(number);
            System.out.println("Added: " + number + ", Buffer size: " + buffer.size());
           
        }
    }
}
