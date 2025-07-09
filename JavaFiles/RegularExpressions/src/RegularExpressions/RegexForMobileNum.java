package RegularExpressions;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexForMobileNum {

	public static void main(String[] args) {
		String Data="""
				9019102589
				7801102589
				8765802589
				8765802589098
				""";
		Pattern Pnum= Pattern.compile("\\b[0-9]{10}\\b");
		
		Matcher pmatch= Pnum.matcher(Data);
		
		var data = pmatch.results().map(m->m.group());

		data.forEach(System.out::println);
	}

}
