package com.dailybasics;
public class DeadLockDemo {
	String pen="0";
	String pencil="1";
	public static void main(String[] args) {
		DeadLockDemo d = new DeadLockDemo();
		Thread t1 = new Thread(()->{
			synchronized(d.pen) {
				System.out.println("Holding pen");
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {
					System.out.println("Thread 1 interrupted ");
				}synchronized(d.pencil) {
					System.out.println("Holding pencil too");
					try {
						Thread.sleep(100);
					}catch(InterruptedException e) {
						System.out.println("Thread 1 interrupted ");
					}
				}
			}
		});
		Thread t2 = new Thread(()->{
			synchronized(d.pencil) {
				System.out.println("Holding pencil");
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {
					System.out.println("Thread 1 interrupted ");
				}synchronized(d.pen) {
					System.out.println("Holding pen too");
					try {
						Thread.sleep(100);
					}catch(InterruptedException e) {
						System.out.println("Thread 1 interrupted ");
					}
				}
			}
		});
		t1.start();
		t2.start();
	}
}
