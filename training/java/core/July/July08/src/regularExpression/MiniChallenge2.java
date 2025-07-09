package regularExpression;

public class MiniChallenge2 {
	public static void main(String[] args) {
		
		String sentance = "The bike is red.";
		
		boolean isMatched = sentance.matches("[A-Z]{1}[a-z ]+[.]");
		System.out.println("is Matched :"+isMatched);
	}
}
