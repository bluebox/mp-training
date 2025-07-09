package miniChallengeThree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniThree {
	 public static void main(String[] args) {
	        String[] test = {
	            "The bike is red, and has flat tires.",
	            "I love being a new L.P.A. student!",
	            "Hello, friends and family: Welcome!",
	            "how are you, Mary?",
	            "this should not match.",
	            "Invalid sentence without punctuation"
	        };
	        String regex = "^[A-Z][\\w\\s\\.,:;!'\"\\-()]*[\\.!?]$";
	        Pattern pattern = Pattern.compile(regex);
	        System.out.println("Matching sentences:");
	        for (String sentence : test)
	        {
	            Matcher matcher = pattern.matcher(sentence);
	            if (matcher.matches()) 
	            {
	                System.out.println( sentence);
	            } 
	            else 
	            {
	                System.out.println( sentence);
	            }
	        }
	    }

}
