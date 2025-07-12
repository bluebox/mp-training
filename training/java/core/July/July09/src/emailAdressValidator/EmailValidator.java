package emailAdressValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	public static void main(String[] args) {
		
		Pattern emailPattern = Pattern.compile("([\\w.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");
		
//		String emailsList = "k";
		 String list = " john.boy@valid.com"+" jane.doe-smith@valid.co.uk"+" jane_Doe1976@valid.co.uk"+" bob!@invalid.com"+" bob-1964@valid.net"+" elanine@valid-test.com.au"+" david@valid.io"+" john.boy@invalid"+" elaineinvalid1983@.com "+"david@invalid..com";
		 Matcher matcher= emailPattern.matcher(list);
	 
		 while(matcher.find()) {
			 System.out.println(matcher.group(1)+"  "+ matcher.group(2));
 
		 }
	}
}
