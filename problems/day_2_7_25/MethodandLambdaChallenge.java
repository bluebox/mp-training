package day_2_7_25;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class MethodandLambdaChallenge {
	public static void main(String [] args) {
            Random random=new Random();
            Scanner sc=new Scanner(System.in);
	     UnaryOperator<String>  Uppercase=(s)->s.toUpperCase();
         
		 UnaryOperator<String>  MiddleInitial=(s)->{
				char value=(char)(64+random.nextInt(26));
				String [] arr=s.split(" ");
				StringBuilder builder=new StringBuilder();
				builder.append(arr[0]);
				builder.append(" ");
				builder.append(value);
				builder.append(" ");
				if(1<arr.length) {
					builder.append(arr[1]);
				}
				return builder.toString();
			};
			
			BiFunction<String, String, String> Lastnameconacte = String::concat;
			
             for(int i=0;i<10;i++) {
        	String input=sc.nextLine();
        	System.out.println(firstname(Uppercase,input));
        	System.out.println(firstname(MiddleInitial,input));
        	System.out.println(lastname(Lastnameconacte,input));
        }
			

	}
	
	public static String firstname(UnaryOperator<String> operator,String value ) {
		 return operator.apply(value);
	}
	
	public static String lastname(BiFunction<String,String,String> operator,String value ) {
		 return operator.apply(value,(new StringBuilder(value)).reverse().toString());
	}
	
}


