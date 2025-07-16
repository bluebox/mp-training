package Practice.july7_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PattrenFound {

	public static void main(String[] args) {
		Pattern myexp=Pattern.compile("[^a-z]",Pattern.CASE_INSENSITIVE);
		Matcher mymatch=myexp.matcher("hi, i am deepika. Hi i am meghana");
		boolean isFound=mymatch.find();
		if(isFound) {
			System.out.println("Hi found");
		}
		else {
			System.out.println("Can't find Hi,");
		}
	}

}
