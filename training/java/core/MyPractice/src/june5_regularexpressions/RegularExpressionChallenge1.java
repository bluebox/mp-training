package june5_regularexpressions;

public class RegularExpressionChallenge1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word="Hello ,World!";
		boolean match=word.matches("Hello ,World!");
		System.out.println(match);
		
		
		word="Hello ,World!";
		match=word.matches("Hello World!");
		System.out.println(match);
	}

}
