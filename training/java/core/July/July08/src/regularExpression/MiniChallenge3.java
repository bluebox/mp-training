package regularExpression;

public class MiniChallenge3 {
	public static void main(String[] args) {
		
		String sentance = "The bike is red, and has flat ties.";
		String sentance2 = "How are you, Mary?";
		
		boolean isMatched = sentance.matches("[A-Z]{1}.*[.!?]");
		System.out.println(sentance+"is Matched :"+isMatched);
		
		boolean isMatched2 = sentance2.matches("[A-Z]{1}.*[.!?]");
		System.out.println(sentance2+"is Matched :"+isMatched2);
	}
}
