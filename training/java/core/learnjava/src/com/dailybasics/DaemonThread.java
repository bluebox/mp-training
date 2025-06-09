package com.dailybasics;

public class DaemonThread {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(()->{
			while(true) {
			System.out.println("Daemon is running !!!");
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				System.out.println("Daemon Interrupted !!!");
			}
		}});
		t1.setDaemon(true);
		t1.start();
		Thread.sleep(2000);
		System.out.println("Main is stopped !");
	}

}
