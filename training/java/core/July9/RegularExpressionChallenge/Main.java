package July9.RegularExpressionChallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	
	public static void main(String[] args) {
		
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
		
		Pattern p = Pattern.compile("^([a-zA-Z0-9._-]+)@([a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,})$");
		Matcher m = p.matcher(emailText);
		m.results().forEach(e -> {
			System.out.printf("UserName : %s, Domain : %s%n", e.group(1),e.group(2));
		});
				
	}

}
