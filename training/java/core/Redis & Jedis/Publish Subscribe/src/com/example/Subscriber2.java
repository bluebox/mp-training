package com.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Subscriber2 {
	public static void main(String[] args) {
		Jedis j=new Jedis();
		JedisPubSub jps=new JedisPubSub() {};
		j.subscribe(jps,"ch1");
	}
}
