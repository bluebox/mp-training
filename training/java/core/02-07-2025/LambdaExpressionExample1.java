
interface Myinterface
{
     void drive();
}



public class LambdaExpressionExample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Myinterface a=()->
			System.out.println("hello");
			
	
		a.drive();
//		Myinterface c1=()->{
//			System.out.println("I am driving BMW car");
//		};
//		c1.drive();

	}

}
