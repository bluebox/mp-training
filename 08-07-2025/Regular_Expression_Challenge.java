package RegularExp;

import java.util.List;


public class Regular_Expression_Challenge {
	
	public static void main(String[] args) {
		
		//mini challenge1
		
		String sentence="Hello,World!!";
		
		boolean Matches=sentence.matches("Hello,World!!");
		
		System.out.println(Matches + "\n" + sentence);
		
		System.out.println();
		
		//minichallenge2
		
		String challenge2="[A-Z].*.\\."; //to check first letter is capital or not.
		
		for(String s:List.of("The bike is red.","I am fine.","what are you doing?","how are you doing?")) {
			
			boolean Match=s.matches(challenge2);
			
			System.out.println(Match + " - " + s);
		}
		System.out.println();
		
		//minichallenge3
		
			String challenge3="[A-Z].+[.?!]"; //to check first letter is capital or not.
				
			for(String s:List.of("The bike is red.","I am fine.","What are you doing?","how are you doing?")) {
					
				boolean Match=s.matches(challenge3);
					
				System.out.println(Match + " - " + s);
				
			}

	}
	

}
