package Practice.july7_regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LastIndex {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a string");
		String myString=sc.nextLine();
		String myWord="a";
		Pattern p=Pattern.compile(myWord);
		Matcher m=p.matcher(myString);
		m.find();
		int lastIndex=m.end();
		System.out.println("a found lastly at :"+lastIndex);
		System.out.println("a found lastly at :"+myString.length());
		sc.close();
	}

}
