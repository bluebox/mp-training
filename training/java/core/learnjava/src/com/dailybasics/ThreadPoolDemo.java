package com.dailybasics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);// 2 Threads
		for (int i = 0; i < 5; i++) {
			int id = i;
			pool.submit(() -> {
				System.out.println("Now id is " + id + " Executing by Thread : " + Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Pool interrupted .");
				}
			});
		}
		pool.shutdown();

	}

}
