package Project;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class MiniChallenge2 {
	public String getEvenCharString(String source) {
		Predicate<Integer> pred=i -> i%2==1;
		UnaryOperator<String> operation=(str) -> {
			StringBuilder outputString = new StringBuilder();
			for(int i=0; i<str.length(); i++) {
				if(pred.test(i)) {
					outputString.append(str.charAt(i));
				}
			}
			return outputString.toString();
		};
		return operation.apply(source);
	}
}
