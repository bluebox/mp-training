import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class IndexMethods {
	
	private static final  String REGEX="\\bhii\\b";
	private static final String pattern="hii hii hii! welcome to medplus";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pattern p=Pattern.compile(REGEX);
		Matcher m=p.matcher(pattern);
		int c=0;
		while(m.find())
		{
			c++;
			System.out.println("Count: "+c);
			System.out.println("Start :"+m.start());
			System.out.println("End :"+m.end());	
		}
		

	}

}
