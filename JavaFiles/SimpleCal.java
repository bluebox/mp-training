
public class SimpleCal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	SimpleCalculator calculator = new SimpleCalculator();
	calculator.setFirstNumber(5.0);
	calculator.setSecondNumber(4);
	System.out.println("Add = " + calculator.getAdditionResult());
	System.out.println("Subtract = " + calculator.getSubtractionResult());
	System.out.println("Multiply = " + calculator.getMultiplicationResult());
	System.out.println("Divide = " + calculator.getDivisionResult());
	}
}