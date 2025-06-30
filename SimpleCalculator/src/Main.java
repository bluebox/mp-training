
public class Main {

	public static void main(String[] args) {
		Calculator c1=new Calculator();
		c1.setFirstNumber(10.0);
		c1.setSecondNumber(0);
		System.out.println("addtion is =" + c1.additionResult());
		System.out.println("subraction is =" + c1.subractionResult());
		System.out.println("multiplication is =" + c1.multiplicationResult());
		System.out.println("division is =" + c1.divisionResult());
		
		
	}
	
}
