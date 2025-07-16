package Day10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChallen {
	public static void main(String[] args) {
		String text="""
				user@example.com
				first.last@subdomain.example.co.uk 
				user-name@example-name.com
				user.example.com
				abc#defu@mail.com
				""";
		Pattern pattern =Pattern.compile("([\\w])+@+([\\w])+\\.([\\w]){2,}");
		String[] strs=text.split("\n");
		for(String s:strs) {
			Matcher match=pattern.matcher(s);
			System.out.println(s+" "+match.matches());
		}
	}
}
