package day9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

	public static void main(String[] args) {
		String pString = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]{2,}$";
		Pattern p = Pattern.compile(pString);
		String email = "surya@gmail";
		Matcher matcher = p.matcher(email);
		System.out.println(matcher.matches());
	}

}
