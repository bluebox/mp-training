package com.example;
import redis.clients.jedis.*;
public class SetJedis {
	public static void main(String[] args) {
		Jedis j=new Jedis();
		j.sadd("s1", "Ram","Ramesh","Ramana","Gopi","Vijay","Ajay","Ram");
		System.out.println(j.smembers("s1"));
		j.sadd("s1","Gopal");
		System.out.println(j.smembers("s1"));
		j.srem("s1", "Gopal");
		j.sadd("s2", "Roy","Remo","Ramesh","Ramana","Gopi","Vijay");
		System.out.println(j.smembers("s2"));
		System.out.println(j.sunion("s1","s2"));
		System.out.println(j.sinter("s1","s2"));
		System.out.println(j.sdiff("s1","s2"));
		System.out.println(j.sdiff("s2","s1"));
		j.sdiffstore("s3", "s1","s2");
		j.sdiffstore("s4", "s2","s1");
		System.out.println(j.sunion("s3","s4"));	
	}
}
