package RegularExpresions;
import java.util.*;

public class minChallenge2 {
public static void main (String[] args) {
	String challenge2 = "[A-Z].*\\.";
	for (String s:List.of("The bike is red.",
			"I am new student",
			"How are you"
			
			)) {
	boolean matched = s.matches(challenge2);
	System.out.println(matched+ ": " +s);
	
	
}
}
}