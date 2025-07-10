package Threads;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		
		Thread thread = Thread.currentThread();
		
		printThreadInfo(thread);
		
		CustomThread CThread =new CustomThread();
		
		printThreadInfo(CThread);
		
		CThread.start();
		
		Runnable rThread =()->{
			
			for (int i = 1; i <= 8; i++) {
                System.out.print(" 2 ");
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
		};
		
		Thread RunnableThread = new Thread(rThread);
		
		printThreadInfo(RunnableThread);
		
		RunnableThread.start();

				
		
		for (int i = 1; i <= 3; i++) {
            System.out.print(" 0 ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		

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
