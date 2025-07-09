package com.prana;

import java.util.List;

public class Mini3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String st = "[A-Z].+[!!]";
        for(String s : List.of("The bird is red." ,"I am ."  )){
        		 boolean m = s.matches(st);	
        		 System.out.println(m);
        }
        
        String st1="^[A-Z].*[.!?]$";;
		System.out.println("matchded strings for challenge 3");
		for(String s3:List.of("The bike is red,and has flat tires","I love being a new L.P.A.student!","Hello,friends and family:Welcome!","How are you,Mary?"))
		{
			boolean matches=s3.matches(st1);
			if(matches)
			{
				System.out.println(s3);
			}
			}  
	}

}
