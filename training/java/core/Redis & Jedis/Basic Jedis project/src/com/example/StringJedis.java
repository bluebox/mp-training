package com.example;
import redis.clients.jedis.Jedis;
public class StringJedis {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Jedis j=new Jedis();
		//Create a key 
		j.set("x", "Ram");
		//Read the key
		System.out.println(j.get("x"));
		//Update the key
		j.set("x", "Vedhansh");
		System.out.println(j.get("x"));
		//Length of the value of the key
		System.out.println("Length of "+j.get("x")+" is : "+j.strlen("x"));
		//To get substring from index 2 to 5 inclusively
		System.out.println("Substring of "+j.get("x")+" between index 2 and 5 inclusively is : "+j.getrange("x", 2, 5));
		//To get till last element use -1 or number larger than or equal to length-1
		System.out.println("Substring of "+j.get("x")+" from index 2 is : "+j.getrange("x", 2, -1));
		//To replace data at a certain index of a String
		System.out.print(j.get("x")+" after replacing index 2 with them is : ");
		j.setrange("x", 2, "them");
		System.out.println(j.get("x"));
		//Check if the key exists
		System.out.println(j.exists("x"));
		//Delete the key
		j.del("x");
		System.out.println(j.get("x"));
		System.out.println(j.exists("x"));
	}
}
