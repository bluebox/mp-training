package Project;
import java.util.function.Predicate;

public class MiniChallenge2 {
	public String getEvenCharString(String source) {
		Predicate<Integer> pred=i -> i%2==1;
		StringBuilder outputString = new StringBuilder(); 
		for(int i=0; i<source.length(); i++) {
			if(pred.test(i)) {
				outputString.append(source.charAt(i));
			}
		}
		return outputString.toString();
	}
}
