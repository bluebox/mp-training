import java.util.List;
import java.util.Scanner;
public class RegularExpressionsMiniChallenges {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string");
		String s=sc.nextLine();
		System.out.println("Enter the string");
		String s1=sc.nextLine();
		boolean match=s.matches(s1);
		System.out.println(match);
		String challenge2="[A-Z].*\\.";
		System.out.println("matched strings for challenge 2");
		for(String s2:List.of("The bike is red.","I am a new Student","hello world","How are you."))
		{
			boolean matches=s2.matches(challenge2);
			if(matches)
			{
				System.out.println(s2);
			}
				
		}
		String challenge3="^[A-Z].*[.!?]$";
		System.out.println("matchded strings for challenge 3");
		for(String s3:List.of("The bike is red,and has flat tires","I love being a new L.P.A.student!","Hello,friends and family:Welcome!","How are you,Mary?"))
		{
			boolean matches=s3.matches(challenge3);
			if(matches)
			{
				System.out.println(s3);
			}
				
		}
	}

}
