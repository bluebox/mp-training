package com.example;
import redis.clients.jedis.*;

public class Publisher {
	public static void main(String[] args) {
		Thread publisher = new Thread(() -> {
		    try (Jedis jedis = new Jedis("localhost", 6379)) {
		        Thread.sleep(1000);
		        jedis.publish("notifications", "Hello, Redis!");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		});
		publisher.start();
	}
}
