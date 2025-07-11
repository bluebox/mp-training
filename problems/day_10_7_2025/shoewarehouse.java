package day_10_7_2025;

import java.util.LinkedList;
import java.util.Queue;

public class shoewarehouse extends Thread {
	public int length=0;
	public static Queue<Integer> warehouse=new LinkedList<>();
	
	public shoewarehouse(int size) {
		length=size;
	}
	
	@Override
	public void run() {
		System.out.println("Shoe warehouse created");
	}
	
	public synchronized void receiveOrder(int order) {
		if(warehouse.size()==this.length) {
			try {
				System.out.println("The opeartion is blocked for a moment need to empty warehouse.");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(warehouse.size()<this.length) {
			notifyAll();
		}
			warehouse.add(order);
			System.out.println("Item added");
		
	}
	
	
	public synchronized void produceorder() {
		if(warehouse.size()==0) {
			try {
				System.out.println("The opeartion is blocked for a moment the warehouse is empty.");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(warehouse.size()>=1) {
			notifyAll();
		}
			System.out.println("The order is : "+warehouse.peek());
			warehouse.remove();
		
	}


}
