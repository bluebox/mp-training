package com.example;
import java.util.HashMap;

import redis.clients.jedis.*;
public class SortedSet {
	public static void main(String[] args) {
		Jedis j=new Jedis();
		j.zadd("z1",4, "Ram");
		j.zadd("z1",1, "Ramesh");
		j.zadd("z1", 2,"Raju");
		j.zadd("z1",2, "Ajay");
		System.out.println(j.zrange("z1", 0, -1));
		j.zadd("z1",1,"Gopal");
		System.out.println(j.zrange("z1", 0, -1));
		HashMap<String, Double> hm = new HashMap<String, Double>();
		j.zrem("z1", "Gopal");
		hm.put("Ram",1d);
		hm.put("Ramesh",1d);
		hm.put("Raj",3d);
		j.zadd("z2", hm);
		System.out.println(j.zrange("z1", 0, -1));
		j.zunionstore("zunion","z1","z2");
		System.out.println(j.zrange("zunion", 0, -1));
		j.zinterstore("zinter","z1","z2");
		System.out.println(j.zrange("zinter", 0, -1));
	}
}
