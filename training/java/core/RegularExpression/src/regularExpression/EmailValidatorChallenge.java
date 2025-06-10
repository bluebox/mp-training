package regularExpression;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorChallenge {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("enter email :");
		String email=sc.nextLine();
		String validatorRE="^([a-zA-Z0-9,_-]+)@([a-zA-Z0-9,_-]+\\.[a-zA-Z].{2,})";
		
		Pattern pattern=Pattern.compile(validatorRE);
		Matcher matcher=pattern.matcher(email);
		if(matcher.matches())
		{
			System.out.println("The Email is valid");
			System.out.println("username "+matcher.group(1));
			System.out.println("Domain name"+matcher.group(2));
			
		}
		else
		{
			System.out.println("Invalid Email");
		}
		
		
	}

}
