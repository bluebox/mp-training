package com.example;
import redis.clients.jedis.*;
import redis.clients.jedis.args.ListDirection;
import redis.clients.jedis.args.ListPosition;
public class ListJedis {
	public static void main(String[] args) {
		//Check if the list exists
		Jedis j=new Jedis("localhost",6379);
		if(j.exists("l")) {
			j.del("l");
		}
		//Add values at the start
		j.lpush("l", "Ram");
		j.lpush("l", "Ramesh","Suresh");
		System.out.println(j.lrange("l", 0, 10));
		//Add values at the end
		j.rpush("l","Raghu","Gopi");
		System.out.println(j.lrange("l", 0, -1));
		//Insert a value in the middle of the list
		j.linsert("l", ListPosition.AFTER,"Raghu", "Raghav");
		//Show all the values in the list
		System.out.println(j.lrange("l", 0, -1));
		//Remove first element from list 
		System.out.println(j.lpop("l"));
		System.out.println(j.lrange("l", 0, -1));
		//Remove last element from list 		
		System.out.println(j.rpop("l"));
		System.out.println(j.lrange("l", 0, -1));
		//Get first index of an element
		System.out.println("First index of Raghav is :"+j.lpos("l","Raghav"));
		System.out.println(j.lrem("l", 2, "Ram"));
		System.out.println(j.lrange("l", 0, -1));
		//Get last index of an element
		System.out.println("Last index of Raghav is :"+j.lpos("l","Raghav"));
		//Remove first two occurences of the element
		System.out.println(j.lrem("l", 2, "Ram"));
		System.out.println(j.lrange("l", 0, -1));
		//Get length of the element
		System.out.println(j.llen("l"));
		//Get element at the index 2
		System.out.println("The value at index 2 is : "+j.lindex("l", 2));	
		//Removes first element if an element is present else wait for some time
		j.lpush("l1", "0");
		System.out.println(j.blpop(3, "l1"));
		System.out.println(j.blpop(3, "l1"));
		System.out.println(j.brpop(3, "l1"));	
	}
}

