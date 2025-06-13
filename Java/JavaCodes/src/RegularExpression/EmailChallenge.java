package RegularExpression;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailChallenge {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
		Pattern pat=Pattern.compile("[a-z][^A-Z]{6,}@[a-z]{2,10}\\.(com|in)");
		Matcher mat=pat.matcher(s);
		if(mat.find()) {
			System.out.println(s+" is a valid email");
		}
		else {
			System.out.println(s+" is not a valid email");
		}
	}

}
