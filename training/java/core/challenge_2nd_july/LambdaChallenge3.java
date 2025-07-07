package challenge_2nd_july;

interface Supplier
{
	public String print();
	
}
public class LambdaChallenge3 {

	public static void main(String[] args) {
		
		Supplier s=()-> "I love java";
		System.out.println(s.print());
		
		String result=s.print();
		System.out.println(result);
	}
	
	

}