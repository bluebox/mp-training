import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PatternClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Pattern p=Pattern.compile("Hello Ravi");
//		Matcher m=p.matcher("Hello Ravi");
//		if(m.matches())
//			System.out.println("Matched");
//		else
//			System.out.println("Not matched");
		
		
		Pattern p=Pattern.compile("medplus");
		Matcher m=p.matcher("Welcome to medplus");
		if(m.find())
		{
			System.out.println("find subsequence");
		}
		else
		{
			System.out.println("Not found");
		}
	}

}
