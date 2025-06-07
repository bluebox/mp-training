package day11.immutability;

import java.util.HashMap;

public class Main {
public static void main(String args[]) {
	
	
	HashMap<String,Integer>h=new HashMap<String,Integer>();
	h.put("E",1);
	Location l=new Location(1,"You must go east",h);
	System.out.println("Location class dont have setter methods and as its fileds are private you cant acess it now");
	
}
}
