package Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EvenOddNumThreads {

	public static void main(String[] args) {
		
		Thread thread = Thread.currentThread();
		
		printThreadInfo(thread);

		EvenOrOddCustomThread CThread= new EvenOrOddCustomThread();
		
		printThreadInfo(CThread);

		
		CThread.start();
		
		Runnable rThread =()->{			
			List<Integer> even = new ArrayList<>();
			
			int e=1;
			
			while(even.size()!=5){
				try {
					Thread.sleep(500);			
				}catch(InterruptedException er) {
					er.setStackTrace(null);
				}
				System.out.println("Running the Even Thread");
				if(e%2==0) {
					even.add(e);
					System.out.println("Even number added to even list");
				}
				e++;
				System.out.println("Even List : "+even);
			}
					
		};
				
		Thread RunnableThread = new Thread(rThread);
		
		printThreadInfo(RunnableThread);
		
		RunnableThread.start();

	}
	
	public static void printThreadInfo(Thread thread) {
		System.out.println("--------------------------------------------");
		System.out.println(thread.getId());
		System.out.println(thread.getName());
		System.out.println(thread.getPriority());
		System.out.println(thread.getClass());
		System.out.println(thread.getState());
		System.out.println(thread.getThreadGroup());
		System.out.println(thread);
		System.out.println("--------------------------------------------");

	}

}
