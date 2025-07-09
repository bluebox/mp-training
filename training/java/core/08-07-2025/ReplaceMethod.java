import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceMethod {
	private static final String REGEX="dog";
	private static String pattern="one dog is drinking milk..all dogs are drinking milk";
	 static String replace="cat";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pattern p=Pattern.compile(REGEX);
		Matcher m=p.matcher(pattern);
		pattern =m.replaceAll(replace);
		System.out.println(pattern);

	}

}
