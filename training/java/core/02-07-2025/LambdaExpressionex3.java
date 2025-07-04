interface Example
{
	public int get(int value);
}
public class LambdaExpressionex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Example e1=(value)->{
			 System.out.println("Hello world");
			 return value;
		 };
		System.out.println("value is "+ e1.get(30));

	}

}
