package RegularExpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValid {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in); 
		Pattern pattern=Pattern.compile("[a-z]{6,100}@[a-z]{2,10}\\.[a-z]{2,5}");
		boolean flag=true;
		while(flag) {
			System.out.println("Enter your email id :");
			String email=scanner.next();
		    Matcher matcher=pattern.matcher(email.trim());
		    if(matcher.find()) {
		    	System.out.println("Valid Email ");
		    	flag=false;
		    }
		    else {
		    	System.out.println("Invalid email,try again");
		    }
		}
		
		
	}

}
