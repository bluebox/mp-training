package RegularExpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniChallenge3 {
public static void main(String[] args) {
	Scanner scanner=new Scanner(System.in);
	Pattern pattern=Pattern.compile("^[A-Z]([a-z\\s\\.A-Z])*[a-z\\s\\.A-Z]+[.!?]");
	boolean flag=true;
	while(flag) {
		System.out.println("Enter your text :");
		String text=scanner.nextLine();
		text.replaceAll("\\p{Punct}","");
	    Matcher matcher=pattern.matcher(text);
	    if(matcher.find()) {
	    	System.out.println("Valid text ");
	    	flag=false;
	    }
	    else {
	    	System.out.println("Invalid text,try again");
	    }
	}
	
}
}
