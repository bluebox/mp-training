package com.example;
import redis.clients.jedis.*;

public class Subscriber1 {
	public static void main(String[] args) {
		try(Jedis j=new Jedis()){
			JedisPubSub jps=new JedisPubSub() {
				@Override
				public void onMessage(String channel,String message) {
					System.out.println(channel+" : "+message);
				}
			};
			j.subscribe(jps,"ch1");
		}
		catch(Exception e) {
			System.out.println("Error occured");
		}
	}
	
}
