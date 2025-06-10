package june5_regularexpressions;

import java.util.regex.*;

public class EmailValidatorChallenge {

	public static void main(String[] args) {
		
		String[] gmails= {"1mohd.sameer@gmail.com","mohdsameergmailcom","mohd@gmail.com",
				"mohammed.sameer$gmail.com","mohammed.sameer@gmail.i"};
		String regex="^[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-z]{2,}$";
		
		Pattern p=Pattern.compile(regex);
		for(String gmail:gmails) {
			Matcher match=p.matcher(gmail);
			if(match.matches()) {
				System.out.println(gmail+" ----->   is matching the given regular expression");
			}
			else {
				System.out.println(gmail+" ---->  is not matching the given regular expression");
			}
		}
		
	}

}
