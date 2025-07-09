package com.prana;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReExp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    
		String emailText = """
		              john.boy@valid.com
	                  john.boy@invalid
		              jane.doe-smith@valid.co.uk
		              jane_Doe1976@valid.co.uk
		              bob-1964@valid.net
		              bob!@invalid.com
	                  elaine@valid-test.com.au
		              elaineinvalid1983@.com
		              david@valid.io
		              david@invalid..com
		""";
	Pattern emailPattern = Pattern.compile( 
			"([\\w.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");
	String[] emailSamples = emailText.lines().toArray(String[]::new);
	for (String email: emailSamples) {
		  email = email.trim();
	      Matcher eMatcher = emailPattern.matcher(email);
	      boolean matched = eMatcher.matches();
	      System.out.print(email + "is" +(matched ? " VALID": " INVALID "));
	      if (matched) {
	             System.out.printf("[username=%s, domain=%s]%n",
	                 eMatcher.group(1),
	                 eMatcher.group(2));
	      }
	     else{
	         System.out.println();
	          }
	    }
	}
}
