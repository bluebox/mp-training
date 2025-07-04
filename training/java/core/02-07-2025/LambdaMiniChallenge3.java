interface Supplier
{
	public String print();
	
}
public class LambdaMiniChallenge3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Supplier iLoveJava=()-> "I love java";
		System.out.println(iLoveJava.print());
		
		String result=iLoveJava.print();
		System.out.println(result);
	}
	
	

}
