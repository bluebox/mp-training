package com.dailybasics;

import java.util.concurrent.locks.ReentrantLock;

class Counter{
	int count=0;
	private final ReentrantLock lock = new ReentrantLock();
	void method() {
		lock.lock();
		try {
			count++;
		}finally{
			lock.unlock();
		}
	}
	public int getCount() {
		return count;
	}

}
public class ReturrentLockDemo {
	public static void main(String[] args) throws InterruptedException{
		Counter c = new Counter();
		Runnable run = (()->{
			for(int i=0;i<1000;i++) {
				c.method();
			}
		});
		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Final count : "+c.count);
		
	}

}
