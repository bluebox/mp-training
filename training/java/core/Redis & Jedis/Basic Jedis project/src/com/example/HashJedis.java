package com.example;
import java.util.HashMap;

import redis.clients.jedis.*;
public class HashJedis {
	public static void main(String[] args) {
		Jedis j=new Jedis("localhost",6379);
		//Create a hashmap
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("1", "Ram");
		hm.put("2", "Shyam");
		hm.put("3", "Raj");
		j.hset("h1", hm);
		//Show the hash map
		System.out.println(j.hgetAll("h1"));
		//Insert into hashmap
		j.hset("h1", "4","Ramana");
		System.out.println(j.hgetAll("h2"));
		//Update a value of a key
		j.hset("h1", "3","Shiva");
		System.out.println(j.hgetAll("h1"));
		//Delete a key from hash
		j.hdel("h1", "2");
		System.out.println(j.hgetAll("h1"));
		//Get length of the hash
		System.out.println("Length of "+j.hgetAll("h1")+" is : "+j.hlen("h1"));
		//Get all keys from the hash
		System.out.println("All the keys from the hash h1 are : "+j.hkeys("h1"));
		//
	}
}
