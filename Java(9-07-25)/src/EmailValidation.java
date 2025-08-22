import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the email");
		String s=sc.nextLine();
		boolean found=false;
		Pattern p1 = Pattern.compile("[a-zA-Z0-9_\\-\\.]+@[a-z]+\\.[a-z]{2,3}");
		Matcher emailMatcher=p1.matcher(s);
		while (emailMatcher.find()) {
            System.out.println(emailMatcher.group()+" is a valid email id");
            found =true;
		}
		if(!found)
		{
			System.out.println(s+" not a valid email id");
		}
		String s3="[a-zA-Z0-9_\\-\\.]+@[a-z]+\\.[a-z]{2,3}";
		boolean matches=s.matches(s3);
		if(matches)
		{
			System.out.println(s+" email is vaild email");
		}
		else
		{
			System.out.println(s+" email is not vaild email");
		}
	}

}
