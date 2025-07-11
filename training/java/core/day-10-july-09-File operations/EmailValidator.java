package day10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	public static void main(String[] args) {
		String pString = "^([\\w.-]+)@((\\w+\\.)+\\w{2,}+)$";
		Pattern p = Pattern.compile(pString);
		String emailsText ="john.boy@valid.com\n"
				+ "john.boy@invalid\n"
				+ "jane.doe-smith@valid.co.uk\n"
				+ "jane_Doe1976@valid.co.uk\n"
				+ "bob-1964@valid.net\n"
				+ "bob!@invalid.com\n"
				+ "elaine@valid-test.com.au\n"
				+ "elaineinvalid1983@.com\n"
				+ "david@valid.io\n"
				+ "david@invalid..com";
		String[] emails = emailsText.split("\n");
		for(String email:emails) {
			Matcher m=p.matcher(email);
			if(m.find()) {
				System.out.println("true -> "+m.group(1)+"->"+m.group(2));
			}
		}
	}

}
