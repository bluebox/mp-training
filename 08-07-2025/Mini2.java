package com.prana;

import java.util.List;

public class Mini2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String st = "[A-Z].*\\.";
        for(String s : List.of("The bird is red." ,"I am ."  )){
        		 boolean m = s.matches(st);	
        		 System.out.println(m);
        }
        
        String st1="[A-Z].*\\.";
		System.out.println("matched strings for challenge 2");
		for(String s2:List.of("The bike is red.","I am a new Student","hello world","How are you."))
		{
			boolean matches=s2.matches(st1);
			if(matches)
			{
				System.out.println(s2);
			}
				
		}

	}
}
