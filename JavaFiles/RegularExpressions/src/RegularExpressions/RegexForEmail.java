package RegularExpressions;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexForEmail {

		public static void main(String[] args) {
			String Data="""
					abc@gmail.com
					email_123@gmail.com.in
					xyz.123_xyz@xyz.org
					pqr-12345@xyz.in
					""";
			
			String regexEmail = "([\\w.-]+)@(([\\w-]+\\.)+[\\w.]{2,})";//"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
			Pattern Pnum= Pattern.compile(regexEmail);
			
			Matcher pmatch= Pnum.matcher(Data);
			
			pmatch.results()
							.forEach(m->System.out.printf("mail: %s\t%nAddress: %s\t%nDomain: %s%n%n", m.group(0),m.group(1),m.group(2)));
		}
}
