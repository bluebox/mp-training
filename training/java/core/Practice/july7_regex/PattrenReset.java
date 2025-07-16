package Practice.july7_regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PattrenReset {

	public static void main(String[] args) throws Exception {
		      Matcher m = Pattern.compile("[frt][hiu][gxe]").matcher("fix the rug with bags");
		      while (m.find())
		    	  System.out.println(m.group());
		      m.reset("fix the rig with rags");
		      while (m.find())
		    	  System.out.println(m.group());
	}
}
