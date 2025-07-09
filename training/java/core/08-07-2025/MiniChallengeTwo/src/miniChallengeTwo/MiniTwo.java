package miniChallengeTwo;

public class MiniTwo {
public static void main(String []args)
{
	String[] words= {"The bike is red.","I am a new student.","hello world.","How are you?"};
	String regex="^[A-Z][a-z\\s]*\\.$";
	 for ( String word: words)
	 {
		System.out.println(word.matches(regex)); 
	 }
}
}
